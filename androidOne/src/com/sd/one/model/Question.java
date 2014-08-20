package com.sd.one.model;

import com.sd.one.model.base.BaseModel;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * [A brief description]
 * 
 * @author devin.hu
 * @version 1.0
 * @date 2013-9-22
 * 
 **/
@XStreamAlias("Question")
public class Question extends BaseModel{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2420123551679316323L;

	@XStreamAsAttribute
	private String QuestionId;

	@XStreamAsAttribute
	private String QuestionLang;

	@XStreamAsAttribute
	private String Text;

	public String getQuestionId() {
		return QuestionId;
	}

	public void setQuestionId(String questionId) {
		QuestionId = questionId;
	}

	public String getQuestionLang() {
		return QuestionLang;
	}

	public void setQuestionLang(String questionLang) {
		QuestionLang = questionLang;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

}
