package net.devwiki.file;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 文件的操作
 * Created by DevWiki on 2016/12/8.
 */

public class FileOperate {

    public static final int TYPE_COPY = 0x001;
    public static final int TYPE_MOVE = 0x002;
    public static final int TYPE_RENAME = 0x003;
    public static final int TYPE_DOWNLOAD = 0x004;
    public static final int TYPE_UPLOAD = 0x005;

    public static OperateBuilder copy(String filePath) {
        return new OperateBuilder(TYPE_COPY, filePath);
    }

    public static OperateBuilder move(String filePath) {
        return new OperateBuilder(TYPE_MOVE, filePath);
    }

    public static OperateBuilder rename(String filePath) {
        return new OperateBuilder(TYPE_RENAME, filePath);
    }

    public static LoaderBuilder download(String filePath) {
        return new LoaderBuilder(TYPE_DOWNLOAD, filePath);
    }

    public static LoaderBuilder upload(String filePath) {
        return new LoaderBuilder(TYPE_UPLOAD, filePath);
    }

    public static class LoaderBuilder {

        private int mLoaderType;
        private String mFilePath;
        private String mUrl;

        LoaderBuilder(int loaderType, String filePath) {
            mLoaderType = loaderType;
            mFilePath = filePath;
        }

        public LoaderBuilder to(String url) {
            mUrl = url;
            return this;
        }

        public LoaderBuilder from(String url) {
            mUrl = url;
            return this;
        }

        public Observable<LoaderResult> start() {
            return null;
        }
    }

    public static class OperateBuilder {

        private int mOperatorType;
        private String mOriginPath;
        private String mTarget;

        OperateBuilder(int operatorType, String originPath) {
            this.mOperatorType = operatorType;
            this.mOriginPath = originPath;
        }

        private int getOperatorType() {
            return mOperatorType;
        }

        public OperateBuilder setOperatorType(int operatorType) {
            mOperatorType = operatorType;
            return this;
        }

        public OperateBuilder setOriginPath(String originPath) {
            mOriginPath = originPath;
            return this;
        }

        public OperateBuilder to(String target) {
            mTarget = target;
            return this;
        }

        public Observable<OperateResult> start() {
            return Observable.create(new Observable.OnSubscribe<OperateResult>() {
                @Override
                public void call(Subscriber<? super OperateResult> subscriber) {
                    if (getOperatorType() == TYPE_COPY) {
                        subscriber.onNext(FileUtil.copy(OperateBuilder.this.mOriginPath, OperateBuilder.this.mTarget));
                    } else if (getOperatorType() == TYPE_MOVE) {

                    } else if (getOperatorType() == TYPE_RENAME) {

                    }
                }
            }).subscribeOn(Schedulers.io());
        }
    }
}
