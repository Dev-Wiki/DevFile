package net.devwiki.file;

import java.io.File;
import java.io.IOException;

import okio.Buffer;
import okio.Okio;
import okio.Sink;
import okio.Source;

/**
 * Created by zyz on 2016/12/13.
 */

public class FileUtil extends net.devwiki.common.util.FileUtil {

    public static OperateResult copy(String originPath, String targetPath) {
        return copy(new File(originPath), new File(targetPath));
    }

    public static OperateResult copy(File originFile, File targetFile) {
        OperateResult operator = new OperateResult();
        operator.setOperateType(OperateResult.TYPE_COPY);
        if (!originFile.exists()) {
            operator.setState(OperateResult.STATE_ERROR);
            return operator;
        }
        Source source = null;
        Sink sink = null;
        try {
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            if (targetFile.exists()) {
                source = Okio.source(originFile);
                sink = Okio.sink(targetFile);
                Buffer buffer = new Buffer();
                long length;
                while ((length = source.read(buffer, 1024)) > 0) {
                    sink.write(buffer, length);
                    sink.flush();
                }
                sink.close();
                source.close();
                operator.setState(OperateResult.STATE_COMPLETE);
            } else {
                operator.setState(OperateResult.STATE_ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            operator.setState(OperateResult.STATE_ERROR);
        } finally {
            try {
                if (sink != null) {
                    sink.close();
                }
                if (source != null) {
                    source.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return operator;
    }
}
