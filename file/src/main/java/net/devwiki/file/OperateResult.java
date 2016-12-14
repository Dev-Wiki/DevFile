package net.devwiki.file;

/**
 * Created by zyz on 2016/12/12.
 */

public class OperateResult {

    public static final int TYPE_COPY = 0x001;
    public static final int TYPE_MOVE = 0x002;

    public static final int STATE_PROCESS = 0x001;
    public static final int STATE_ERROR = 0x002;
    public static final int STATE_COMPLETE = 0x003;

    private int mOperateType;
    private float mProgress;
    private int mState;
    private long mTotalSize;
    private long mHadSize;

    public int getOperateType() {
        return mOperateType;
    }

    public OperateResult setOperateType(int operateType) {
        mOperateType = operateType;
        return this;
    }

    public float getProgress() {
        return mProgress;
    }

    public OperateResult setProgress(float progress) {
        mProgress = progress;
        return this;
    }

    public int getState() {
        return mState;
    }

    public OperateResult setState(int state) {
        mState = state;
        return this;
    }

    public long getTotalSize() {
        return mTotalSize;
    }

    public OperateResult setTotalSize(long totalSize) {
        mTotalSize = totalSize;
        return this;
    }

    public long getHadSize() {
        return mHadSize;
    }

    public OperateResult setHadSize(long hadSize) {
        mHadSize = hadSize;
        return this;
    }

    @Override
    public String toString() {
        return "OperateResult{" +
                "mOperateType=" + mOperateType +
                ", mProgress=" + mProgress +
                ", mState=" + mState +
                ", mTotalSize=" + mTotalSize +
                ", mHadSize=" + mHadSize +
                '}';
    }
}
