package com.example.student.doit_multimemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.student.doit_multimemo.common.TitleBitmapButton;

import java.io.File;
import java.io.FileOutputStream;

// 손글씨 입력 액티비티
public class HandwritingMakingActivity extends AppCompatActivity {
	private static final String TAG = "HandwritingActivity";

	HandwritingView mWritingBoard; // 페인트 보드 변수 선언

    // 버튼 변수 선언
	TitleBitmapButton mColorBtn;
	TitleBitmapButton mPenBtn;
	TitleBitmapButton mEraserBtn;
	TitleBitmapButton mUndoBtn;

	LinearLayout mAddedLayout;

	// 현재 선택된 색상, 글씨 크기 표시를 위한 변수선언
	TitleBitmapButton mColorLegendBtn;
	TextView mSizeLegendTxt;

	int mColor = 0xff000000;
	int mSize = 8;
	int mOldColor;
	int mOldSize;
	boolean mEraserSelected = false;

	TitleBitmapButton mHandwritingMakingSaveBtn;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// XML 레이아웃 설정
		setContentView(R.layout.handwriting_making_activity);

		setTopLayout();

		setBottomLayout();

		setWritingBorad();

    }

	public void setWritingBorad() {

	    // 페인트보드 객체를 추가할 레이아웃 참조
		LinearLayout boardLayout = (LinearLayout) findViewById(R.id.boardLayout);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.FILL_PARENT,
        		LinearLayout.LayoutParams.FILL_PARENT);

        // 페인트보드 객체 생성
        mWritingBoard = new HandwritingView(this);
        mWritingBoard.setLayoutParams(params);
        mWritingBoard.setPadding(2, 2, 2, 2);

        // 페인트보드 객체를 보드 레이아웃에 추가
        boardLayout.addView(mWritingBoard);
	}

	public void setTopLayout() {
		LinearLayout toolsLayout = (LinearLayout) findViewById(R.id.toolsLayout);

		mColorBtn = (TitleBitmapButton) findViewById(R.id.colorBtn);
		mPenBtn = (TitleBitmapButton) findViewById(R.id.penBtn);
		mEraserBtn = (TitleBitmapButton) findViewById(R.id.eraserBtn);
		mUndoBtn = (TitleBitmapButton) findViewById(R.id.undoBtn);

		// [색상] 버튼 클릭 시 이벤트 처리
        mColorBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {

        		ColorPaletteDialog.mSelectedListener = new OnColorSelectedListener() {
        			public void onColorSelected(int color) {
        				mColor = color;
        				mWritingBoard.updatePaintProperty(mColor, mSize);
        				displayPaintProperty();
        			}
        		};


        		// [색상 선택] 대화상자 띄우기
        		Intent intent = new Intent(getApplicationContext(), ColorPaletteDialog.class);
        		startActivity(intent);

        	}
        });
        // [굵기] 버튼 클릭 시 이벤트 처리
        mPenBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {

        		PenPaletteDialog.mSelectedListener = new OnPenSelectedListener() {
        			public void onPenSelected(int size) {
        				mSize = size;
        				mWritingBoard.updatePaintProperty(mColor, mSize);
        				displayPaintProperty();
        			}
        		};


                // [굵기 선택] 대화상자 띄우기
        		Intent intent = new Intent(getApplicationContext(), PenPaletteDialog.class);
        		startActivity(intent);

        	}
        });

        // [지우개] 버튼 클릭 시 이벤트 처리
        mEraserBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {

        		mEraserSelected = !mEraserSelected;

        		if (mEraserSelected) {
        		    // 지우개가 선택되면 나머지 버튼 클릭 비활성화
        			mColorBtn.setEnabled(false);
        			mPenBtn.setEnabled(false);
        			mUndoBtn.setEnabled(false);

                    mColorBtn.invalidate();
                    mPenBtn.invalidate();
                    mUndoBtn.invalidate();

                    // 현재 색상 및 굵기 표시창을 지우개로 바꾸고 원래 있던 값 임시 변수에 저장
                    mOldColor = mColor;
                    mOldSize = mSize;
                    mColor = Color.WHITE;
                    mSize = 15;
                    mWritingBoard.updatePaintProperty(mColor, mSize);
                    displayPaintProperty();

                } else {
        		    // 지우개 끝나면 나머지 버튼 활성화
                	mColorBtn.setEnabled(true);
                	mPenBtn.setEnabled(true);
                	mUndoBtn.setEnabled(true);

                    mColorBtn.invalidate();
                    mPenBtn.invalidate();
                    mUndoBtn.invalidate();

                    // 현재 색상 및 굵기 표시창의 지우개 값을 원래 있던 값으로 변경
                    mColor = mOldColor;
                    mSize = mOldSize;
                    mWritingBoard.updatePaintProperty(mColor, mSize);
                    displayPaintProperty();

                }

        	}
        });

        // [취소] 버튼 클릭 시 이벤트 처리
        mUndoBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Log.d(TAG, "undo button clicked.");

        		mWritingBoard.undo();
        	}
        });

        // 색상 버튼 및 굵기 텍스트뷰 추가
        LinearLayout.LayoutParams addedParams = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.FILL_PARENT,
        		LinearLayout.LayoutParams.FILL_PARENT);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.FILL_PARENT,
        		LinearLayout.LayoutParams.WRAP_CONTENT);
        mAddedLayout = new LinearLayout(this);
        mAddedLayout.setLayoutParams(addedParams);
        mAddedLayout.setOrientation(LinearLayout.VERTICAL);
        mAddedLayout.setPadding(8,8,8,8);

        LinearLayout outlineLayout = new LinearLayout(this);
        outlineLayout.setLayoutParams(buttonParams);
        outlineLayout.setOrientation(LinearLayout.VERTICAL);
        outlineLayout.setBackgroundColor(Color.LTGRAY);
        outlineLayout.setPadding(1,1,1,1);

        // 현재 선택된 색상 및 굵기 표시
        mColorLegendBtn = new TitleBitmapButton(this);
        mColorLegendBtn.setEnabled(false);
        mColorLegendBtn.setLayoutParams(buttonParams);
        mColorLegendBtn.setText(" ");
        mColorLegendBtn.setBackgroundColor(mColor);
        mColorLegendBtn.setHeight(20);
        outlineLayout.addView(mColorLegendBtn);
        mAddedLayout.addView(outlineLayout);

        mSizeLegendTxt = new TextView(this);
        mSizeLegendTxt.setLayoutParams(buttonParams);
        mSizeLegendTxt.setText("Size : " + mSize);
        mSizeLegendTxt.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        mSizeLegendTxt.setTextSize(16);
        mSizeLegendTxt.setTextColor(Color.BLACK);
        mAddedLayout.addView(mSizeLegendTxt);

        toolsLayout.addView(mAddedLayout);
	}

	public void setBottomLayout()
	{
        // 저장 버튼 클릭 이벤트 처리
		mHandwritingMakingSaveBtn = (TitleBitmapButton)findViewById(R.id.handwriting_making_saveBtn);

		mHandwritingMakingSaveBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				saveHandwritingMaking();
			}
		});
	}

    public int getChosenColor() {
    	return mColor;
    }

    public int getPenThickness() {
    	return mSize;
    }

    private void displayPaintProperty() {
	    // 현재 색상 및 굵기 표시
    	mColorLegendBtn.setBackgroundColor(mColor);
    	mSizeLegendTxt.setText("Size : " + mSize);

    	mAddedLayout.invalidate();
    }

    public void saveHandwritingMaking() {

    	try {
    		checkHandwritingFolder();

    		// 기존에 만들어진 이미지 파일이 있을 경우 삭제
        	String handwritingName = "made";

        	File file = new File(BasicInfo.FOLDER_HANDWRITING + handwritingName);
        	if(file.exists()) {
        		file.delete();
        	}

        	// 손글씨 이미지를 저장할 스트림 객체 생성
			FileOutputStream outstream = new FileOutputStream(BasicInfo.FOLDER_HANDWRITING + handwritingName);

			Bitmap mBitmap = mWritingBoard.getImage();

			// 손글씨 이미지 저장
			mBitmap.compress(Bitmap.CompressFormat.PNG, 100, outstream);
			outstream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		setResult(RESULT_OK);
		finish();
	}

    public void checkHandwritingFolder() {
	    // 손글씨 저장 될 폴더 존재 여부 확인 메서드
    	File handwritingFolder = new File(BasicInfo.FOLDER_HANDWRITING);
		if(!handwritingFolder.isDirectory()){
			Log.d(TAG, "creating handwriting folder : " + handwritingFolder);

			handwritingFolder.mkdirs();
		}
    }
}
