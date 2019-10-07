package org.yakindu.scr.callhandling;

import org.yakindu.scr.IStatemachine;
import org.yakindu.scr.ITimerCallback;

public interface ICallHandlingStatemachine extends ITimerCallback,IStatemachine {

	public interface SCInterface {
	
	}
	
	public SCInterface getSCInterface();
	
	public interface SCIUser {
	
		public void raiseAccept_call();
		
		public void raiseDismiss_call();
		
	}
	
	public SCIUser getSCIUser();
	
	public interface SCIPhone {
	
		public void raiseIncoming_call();
		
		public void raiseApply_adapt();
		
		public boolean getChanges();
		
		public void setChanges(boolean value);
		
		public boolean getConfig__();
		
		public void setConfig__(boolean value);
		
		public long getCall_duration();
		
		public void setCall_duration(long value);
		
		public void setSCIPhoneOperationCallback(SCIPhoneOperationCallback operationCallback);
	
	}
	
	public interface SCIPhoneOperationCallback {
	
		public void apply_hanges();
		
	}
	
	public SCIPhone getSCIPhone();
	
	public interface SCIAdapt_layer {
	
		public void raiseAdapt_need();
		
		public void raiseApply_adapt();
		
		public boolean getConfig__();
		
		public void setConfig__(boolean value);
		
		public boolean getChanges();
		
		public void setChanges(boolean value);
		
		public void setSCIAdapt_layerOperationCallback(SCIAdapt_layerOperationCallback operationCallback);
	
	}
	
	public interface SCIAdapt_layerOperationCallback {
	
		public void apply_hanges();
		
	}
	
	public SCIAdapt_layer getSCIAdapt_layer();
	
	public interface SCIMeta_layer {
	
		public void raiseAdapt_need();
		
		public boolean getConfig__();
		
		public void setConfig__(boolean value);
		
	}
	
	public SCIMeta_layer getSCIMeta_layer();
	
}
