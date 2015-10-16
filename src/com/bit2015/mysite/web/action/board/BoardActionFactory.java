package com.bit2015.mysite.web.action.board;

import com.bit2015.web.action.Action;
import com.bit2015.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		// TODO Auto-generated method stub
		Action action=null;
		if ("writeform".equals(actionName)) {
			action = new WriteFormAction();
		}else if ("write".equals(actionName)) {
			action = new WriteAction();
		}else if ("modify".equals(actionName)) {
			action = new ModifyAction();
		}else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		}else if ("update".equals(actionName)) {
			action = new UpdateAction();
		}  else if ("view".equals(actionName)) {
			action = new ViewAction();
		}else {
			action = new ListAction();
		}
		return action;
	}

}
