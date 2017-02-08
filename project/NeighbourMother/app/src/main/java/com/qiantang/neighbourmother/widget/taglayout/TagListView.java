/**
 * 
 */
package com.qiantang.neighbourmother.widget.taglayout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;


import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.logic.BbsTagUtil;
import com.qiantang.neighbourmother.model.TagObj;
import com.qiantang.neighbourmother.util.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TagListView extends FlowLayout {

	private final List<TagObj> mTags = new ArrayList<TagObj>();
private BbsTagUtil bbsTagUtil=new BbsTagUtil();
	/**
	 * @param context
	 */
	public TagListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attributeSet
	 */
	public TagListView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attributeSet
	 * @param defStyle
	 */
	public TagListView(Context context, AttributeSet attributeSet, int defStyle) {
		super(context, attributeSet, defStyle);
		// TODO Auto-generated constructor stub

	}

	private int alreadyPosition1=-1;
	private int alreadyPosition2=-1;
	private int alreadyPosition3=-1;
	private void inflateTagView(final TagObj t,final int position) {

		TagView localTagView = (TagView) View.inflate(getContext(),
				R.layout.tag, null);
		bbsTagUtil.setViewDrawable(getContext(),localTagView,Color.parseColor("#"+t.getColor()));

		localTagView.setText(t.getName());
		localTagView.setTag(t);
		localTagView.setSelected(t.isChecked());
		localTagView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
					setChooerLogic(position);
				calcuAcount();
			}
		});
		addView(localTagView);
	}

	public List<TagObj> getmTags(){
		return mTags;
	}

	public void setTags(List<? extends TagObj> lists) {
		removeAllViews();
		mTags.clear();

		for (int i = 0; i < lists.size(); i++) {
			TagObj tag=lists.get(i);
			if(tag.isChecked())
				alreadyPosition3=i;
			mTags.add(tag);
			inflateTagView(tag,i);
		}
	}


	private void setChooerLogic(int position){
		TagObj tag=mTags.get(position);
		if(!tag.isChecked()){
			if(alreadyPosition1!=-1){

				((TagView) getChildAt(alreadyPosition1)).setSelected(false);
				mTags.get(alreadyPosition1).setChecked(false);

				((TagView) getChildAt(position)).setSelected(true);
				mTags.get(position).setChecked(true);

				alreadyPosition1=alreadyPosition2;
				alreadyPosition2=alreadyPosition3;
				alreadyPosition3=position;
			}else{
				alreadyPosition1=alreadyPosition2;
				alreadyPosition2=alreadyPosition3;
				alreadyPosition3=position;
				((TagView) getChildAt(alreadyPosition3)).setSelected(true);
				mTags.get(alreadyPosition3).setChecked(true);
			}
		}else{

			if(position==alreadyPosition1){
				if(alreadyPosition2!=-1||alreadyPosition3!=-1){
					((TagView) getChildAt(position)).setSelected(false);
					mTags.get(position).setChecked(false);
					alreadyPosition1=-1;
				}

			}else if(position==alreadyPosition2){
				if(alreadyPosition1!=-1||alreadyPosition3!=-1) {

					((TagView) getChildAt(position)).setSelected(false);
					mTags.get(position).setChecked(false);

					if (alreadyPosition1 == -1)
						alreadyPosition2 = -1;
					else {
						alreadyPosition2 = alreadyPosition1;
						alreadyPosition1 = -1;
					}
				}
			}else if(position==alreadyPosition3){
				if(alreadyPosition1!=-1||alreadyPosition2!=-1) {

					((TagView) getChildAt(position)).setSelected(false);
					mTags.get(position).setChecked(false);

					if(alreadyPosition1!=-1&&alreadyPosition2!=-1){
						alreadyPosition3=alreadyPosition2;
						alreadyPosition2=alreadyPosition1;
						alreadyPosition1=-1;
					}else if(alreadyPosition1!=-1){
						alreadyPosition3=alreadyPosition1;
						alreadyPosition1=-1;
						alreadyPosition2=-1;
					}else if(alreadyPosition2!=-1){
						alreadyPosition3=alreadyPosition2;
						alreadyPosition1=-1;
						alreadyPosition2=-1;
					}
				}
			}
		}
	}

	private void calcuAcount(){
		int acount=0;
		if(alreadyPosition1!=-1)
			acount++;
		if(alreadyPosition2!=-1)
			acount++;
		if(alreadyPosition3!=-1)
			acount++;

		if(tagCountListener!=null)
			tagCountListener.acount(acount);

	}

	private TagCountListener tagCountListener;
	public void setTagCountListener(TagCountListener tagCountListener){
		this.tagCountListener=tagCountListener;
	}
	public interface TagCountListener{
		public void acount(int acount);
	}
}
