package com.example.student.doit_multimemo;

// 메모 리스트 아이템 객체
public class MemoListItem {

    private String[] mData;
    private String mId;
    private boolean mSelectable = true;

    // 메모 객체 생성자
    public MemoListItem(String itemId, String[] obj) {
        mId = itemId;
        mData = obj;
    }

    public MemoListItem(String memoId, String memoDate, String memoText,
                        String id_handwriting, String uri_handwriting,
                        String id_photo, String uri_photo,
                        String id_video, String uri_video,
                        String id_voice, String uri_voice) {
        mId = memoId;
        mData = new String[10];
        mData[0] = memoDate;
        mData[1] = memoText;
        mData[2] = id_handwriting;
        mData[3] = uri_handwriting;
        mData[4] = id_photo;
        mData[5] = uri_photo;
        mData[6] = id_video;
        mData[7] = uri_video;
        mData[8] = id_voice;
        mData[9] = uri_voice;
    }

    public boolean isSelectable() {
        return mSelectable;
    }

    public void setSelectable(boolean selectable) {
        mSelectable = selectable;
    }

    // Id 게터 & 세터
    public String getId() {
        return mId;
    }
    public void setId(String itemId) {
        mId = itemId;
    }

    // Data 게터 & 세터
    public String[] getData() {
        return mData;
    }
    public String getData(int index) {
        if (mData == null || index >= mData.length) {
            return null;
        }

        return mData[index];
    }
    public void setData(String[] obj) {
        mData = obj;
    }

    public int compareTo(MemoListItem other) {
        if (mData != null) {
            Object[] otherData = other.getData();
            if (mData.length == otherData.length) {
                for (int i = 0; i < mData.length; i++) {
                    if (!mData[i].equals(otherData[i])) {
                        return -1;
                    }
                }
            } else {
                return -1;
            }
        } else {
            throw new IllegalArgumentException();
        }
        return 0;
    }
}
