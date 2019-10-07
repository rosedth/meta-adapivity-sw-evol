package org.yakindu.scr.callhandling;
import org.yakindu.scr.ITimer;

public class CallHandlingStatemachine implements ICallHandlingStatemachine {

	protected class SCInterfaceImpl implements SCInterface {
	
	}
	
	protected SCInterfaceImpl ___nterface;
	
	protected class SCIUserImpl implements SCIUser {
	
		private boolean accept_call;
		
		public void raiseAccept_call() {
			accept_call = true;
		}
		
		private boolean dismiss_call;
		
		public void raiseDismiss_call() {
			dismiss_call = true;
		}
		
		protected void clearEvents() {
			accept_call = false;
			dismiss_call = false;
		}
	}
	
	protected SCIUserImpl ____ser;
	
	protected class SCIPhoneImpl implements SCIPhone {
	
		private SCIPhoneOperationCallback operationCallback;
		
		public void setSCIPhoneOperationCallback(
				SCIPhoneOperationCallback operationCallback) {
			this.operationCallback = operationCallback;
		}
		private boolean incoming_call;
		
		public void raiseIncoming_call() {
			incoming_call = true;
		}
		
		private boolean apply_adapt;
		
		public void raiseApply_adapt() {
			apply_adapt = true;
		}
		
		private boolean changes;
		
		public boolean getChanges() {
			return changes;
		}
		
		public void setChanges(boolean value) {
			this.changes = value;
		}
		
		private boolean config__;
		
		public boolean getConfig__() {
			return config__;
		}
		
		public void setConfig__(boolean value) {
			this.config__ = value;
		}
		
		private long call_duration;
		
		public long getCall_duration() {
			return call_duration;
		}
		
		public void setCall_duration(long value) {
			this.call_duration = value;
		}
		
		protected void clearEvents() {
			incoming_call = false;
			apply_adapt = false;
		}
	}
	
	protected SCIPhoneImpl ____hone;
	
	protected class SCIAdapt_layerImpl implements SCIAdapt_layer {
	
		private SCIAdapt_layerOperationCallback operationCallback;
		
		public void setSCIAdapt_layerOperationCallback(
				SCIAdapt_layerOperationCallback operationCallback) {
			this.operationCallback = operationCallback;
		}
		private boolean adapt_need;
		
		public void raiseAdapt_need() {
			adapt_need = true;
		}
		
		private boolean apply_adapt;
		
		public void raiseApply_adapt() {
			apply_adapt = true;
		}
		
		private boolean config__;
		
		public boolean getConfig__() {
			return config__;
		}
		
		public void setConfig__(boolean value) {
			this.config__ = value;
		}
		
		private boolean changes;
		
		public boolean getChanges() {
			return changes;
		}
		
		public void setChanges(boolean value) {
			this.changes = value;
		}
		
		protected void clearEvents() {
			adapt_need = false;
			apply_adapt = false;
		}
	}
	
	protected SCIAdapt_layerImpl ____dapt_layer;
	
	protected class SCIMeta_layerImpl implements SCIMeta_layer {
	
		private boolean adapt_need;
		
		public void raiseAdapt_need() {
			adapt_need = true;
		}
		
		private boolean config__;
		
		public boolean getConfig__() {
			return config__;
		}
		
		public void setConfig__(boolean value) {
			this.config__ = value;
		}
		
		protected void clearEvents() {
			adapt_need = false;
		}
	}
	
	protected SCIMeta_layerImpl ____eta_layer;
	
	private boolean initialized = false;
	
	public enum State {
		_ystem__ayer__dle,
		_ystem__ayer__ncoming__all,
		_ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig,
		_ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes,
		_ystem__ayer__ctive__all,
		_ystem__ayer__ismiss__all,
		_ystem__ayer__rocess__all,
		_daptation__ayer__onitoring,
		_daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig,
		_daptation__ayer__onitoring___onitoring__egion__pply_changes,
		_daptation__ayer__nalyzing,
		_daptation__ayer__lanning,
		_daptation__ayer__xecuting,
		_eta_daptation__ayer__onitoring,
		_eta_daptation__ayer__nalyzing,
		_eta_daptation__ayer__lanning,
		_eta_daptation__ayer__xecuting,
		$NullState$
	};
	
	private final State[] stateVector = new State[3];
	
	private int nextStateIndex;
	
	private ITimer timer;
	
	private final boolean[] timeEvents = new boolean[2];
	public CallHandlingStatemachine() {
		___nterface = new SCInterfaceImpl();
		____ser = new SCIUserImpl();
		____hone = new SCIPhoneImpl();
		____dapt_layer = new SCIAdapt_layerImpl();
		____eta_layer = new SCIMeta_layerImpl();
	}
	
	public void init() {
		this.initialized = true;
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		for (int i = 0; i < 3; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		____hone.setChanges(false);
		
		____hone.setConfig__(true);
		
		____hone.setCall_duration(0);
		
		____dapt_layer.setConfig__(true);
		
		____dapt_layer.setChanges(false);
		
		____eta_layer.setConfig__(true);
	}
	
	public void enter() {
		if (!initialized) {
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		}
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		enter_equence__ystem__ayer_default();
		enter_equence__daptation__ayer_default();
		enter_equence__eta_daptation__ayer_default();
	}
	
	public void exit() {
		exit_equence__ystem__ayer();
		exit_equence__daptation__ayer();
		exit_equence__eta_daptation__ayer();
	}
	
	/**
	 * @see IStatemachine#isActive()
	 */
	public boolean isActive() {
		return stateVector[0] != State.$NullState$||stateVector[1] != State.$NullState$||stateVector[2] != State.$NullState$;
	}
	
	/** 
	* Always returns 'false' since this state machine can never become final.
	*
	* @see IStatemachine#isFinal()
	*/
	public boolean isFinal() {
		return false;
	}
	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {
		____ser.clearEvents();
		____hone.clearEvents();
		____dapt_layer.clearEvents();
		____eta_layer.clearEvents();
		for (int i=0; i<timeEvents.length; i++) {
			timeEvents[i] = false;
		}
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public boolean isStateActive(State state) {
	
		switch (state) {
		case _ystem__ayer__dle:
			return stateVector[0] == State._ystem__ayer__dle;
		case _ystem__ayer__ncoming__all:
			return stateVector[0].ordinal() >= State.
					_ystem__ayer__ncoming__all.ordinal()&& stateVector[0].ordinal() <= State._ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes.ordinal();
		case _ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig:
			return stateVector[0] == State._ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig;
		case _ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes:
			return stateVector[0] == State._ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes;
		case _ystem__ayer__ctive__all:
			return stateVector[0] == State._ystem__ayer__ctive__all;
		case _ystem__ayer__ismiss__all:
			return stateVector[0] == State._ystem__ayer__ismiss__all;
		case _ystem__ayer__rocess__all:
			return stateVector[0] == State._ystem__ayer__rocess__all;
		case _daptation__ayer__onitoring:
			return stateVector[1].ordinal() >= State.
					_daptation__ayer__onitoring.ordinal()&& stateVector[1].ordinal() <= State._daptation__ayer__onitoring___onitoring__egion__pply_changes.ordinal();
		case _daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig:
			return stateVector[1] == State._daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig;
		case _daptation__ayer__onitoring___onitoring__egion__pply_changes:
			return stateVector[1] == State._daptation__ayer__onitoring___onitoring__egion__pply_changes;
		case _daptation__ayer__nalyzing:
			return stateVector[1] == State._daptation__ayer__nalyzing;
		case _daptation__ayer__lanning:
			return stateVector[1] == State._daptation__ayer__lanning;
		case _daptation__ayer__xecuting:
			return stateVector[1] == State._daptation__ayer__xecuting;
		case _eta_daptation__ayer__onitoring:
			return stateVector[2] == State._eta_daptation__ayer__onitoring;
		case _eta_daptation__ayer__nalyzing:
			return stateVector[2] == State._eta_daptation__ayer__nalyzing;
		case _eta_daptation__ayer__lanning:
			return stateVector[2] == State._eta_daptation__ayer__lanning;
		case _eta_daptation__ayer__xecuting:
			return stateVector[2] == State._eta_daptation__ayer__xecuting;
		default:
			return false;
		}
	}
	
	/**
	* Set the {@link ITimer} for the state machine. It must be set
	* externally on a timed state machine before a run cycle can be correct
	* executed.
	* 
	* @param timer
	*/
	public void setTimer(ITimer timer) {
		this.timer = timer;
	}
	
	/**
	* Returns the currently used timer.
	* 
	* @return {@link ITimer}
	*/
	public ITimer getTimer() {
		return timer;
	}
	
	public void timeElapsed(int eventID) {
		timeEvents[eventID] = true;
	}
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public SCIUser getSCIUser() {
		return sCIUser;
	}
	
	public SCIPhone getSCIPhone() {
		return sCIPhone;
	}
	
	public SCIAdapt_layer getSCIAdapt_layer() {
		return sCIAdapt_layer;
	}
	
	public SCIMeta_layer getSCIMeta_layer() {
		return sCIMeta_layer;
	}
	
	private boolean check__ystem__ayer__dle_tr__tr_() {
		return ____hone.incoming_call;
	}
	
	private boolean check__ystem__ayer__ncoming__all_tr__tr_() {
		return ____hone.getConfig__()==true;
	}
	
	private boolean check__ystem__ayer_check__ystem__ayer__ncoming__all_tr__tr_() {
		return ____hone.apply_adapt;
	}
	
	private boolean check__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig_tr__tr_() {
		return ____hone.getChanges()==true;
	}
	
	private boolean check__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes_tr__tr_() {
		return true;
	}
	
	private boolean check__ystem__ayer__ctive__all_tr__tr_() {
		return ____ser.dismiss_call;
	}
	
	private boolean check__ystem__ayer__ctive__all_lr__lr_() {
		return timeEvents[0];
	}
	
	private boolean check__ystem__ayer__ismiss__all_tr__tr_() {
		return timeEvents[1];
	}
	
	private boolean check__ystem__ayer__rocess__all_tr__tr_() {
		return ____ser.accept_call;
	}
	
	private boolean check__ystem__ayer_check__ystem__ayer__rocess__all_tr__tr_() {
		return ____ser.dismiss_call;
	}
	
	private boolean check__daptation__ayer__onitoring_tr__tr_() {
		return (____dapt_layer.adapt_need) && (____dapt_layer.getChanges()==false);
	}
	
	private boolean check__daptation__ayer_check__daptation__ayer__onitoring_tr__tr_() {
		return ____dapt_layer.apply_adapt;
	}
	
	private boolean check__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig_tr__tr_() {
		return ____dapt_layer.getChanges()==true;
	}
	
	private boolean check__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig_lr__lr_() {
		return ____dapt_layer.getConfig__()==false;
	}
	
	private boolean check__daptation__ayer__onitoring___onitoring__egion__pply_changes_tr__tr_() {
		return true;
	}
	
	private boolean check__daptation__ayer__nalyzing_tr__tr_() {
		return true;
	}
	
	private boolean check__daptation__ayer__lanning_tr__tr_() {
		return true;
	}
	
	private boolean check__daptation__ayer__xecuting_tr__tr_() {
		return true;
	}
	
	private boolean check__eta_daptation__ayer__onitoring_tr__tr_() {
		return ____eta_layer.adapt_need;
	}
	
	private boolean check__eta_daptation__ayer__nalyzing_tr__tr_() {
		return true;
	}
	
	private boolean check__eta_daptation__ayer__lanning_tr__tr_() {
		return true;
	}
	
	private boolean check__eta_daptation__ayer__xecuting_tr__tr_() {
		return true;
	}
	
	private void effect__ystem__ayer__dle_tr_() {
		exit_equence__ystem__ayer__dle();
		enter_equence__ystem__ayer__ncoming__all_default();
	}
	
	private void effect__ystem__ayer__ncoming__all_tr_() {
		exit_equence__ystem__ayer__ncoming__all();
		enter_equence__ystem__ayer__rocess__all_default();
	}
	
	private void effect__ystem__ayer_effect__ystem__ayer__ncoming__all_tr_() {
		exit_equence__ystem__ayer__ncoming__all();
		enter_equence__ystem__ayer__ncoming__all_default();
	}
	
	private void effect__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig_tr_() {
		exit_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig();
		enter_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes_default();
	}
	
	private void effect__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes_tr_() {
		exit_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes();
		enter_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig_default();
	}
	
	private void effect__ystem__ayer__ctive__all_tr_() {
		exit_equence__ystem__ayer__ctive__all();
		enter_equence__ystem__ayer__ismiss__all_default();
	}
	
	private void effect__ystem__ayer__ctive__all_lr__lr_() {
		____hone.setCall_duration(____hone.getCall_duration() + 1);
	}
	
	private void effect__ystem__ayer__ismiss__all_tr_() {
		exit_equence__ystem__ayer__ismiss__all();
		enter_equence__ystem__ayer__dle_default();
	}
	
	private void effect__ystem__ayer__rocess__all_tr_() {
		exit_equence__ystem__ayer__rocess__all();
		enter_equence__ystem__ayer__ctive__all_default();
	}
	
	private void effect__ystem__ayer_effect__ystem__ayer__rocess__all_tr_() {
		exit_equence__ystem__ayer__rocess__all();
		enter_equence__ystem__ayer__ismiss__all_default();
	}
	
	private void effect__daptation__ayer__onitoring_tr_() {
		exit_equence__daptation__ayer__onitoring();
		enter_equence__daptation__ayer__nalyzing_default();
	}
	
	private void effect__daptation__ayer_effect__daptation__ayer__onitoring_tr_() {
		exit_equence__daptation__ayer__onitoring();
		enter_equence__daptation__ayer__onitoring_default();
	}
	
	private void effect__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig_tr_() {
		exit_equence__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig();
		enter_equence__daptation__ayer__onitoring___onitoring__egion__pply_changes_default();
	}
	
	private void effect__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig_lr__lr_() {
		____eta_layer.raiseAdapt_need();
	}
	
	private void effect__daptation__ayer__onitoring___onitoring__egion__pply_changes_tr_() {
		exit_equence__daptation__ayer__onitoring___onitoring__egion__pply_changes();
		enter_equence__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig_default();
	}
	
	private void effect__daptation__ayer__nalyzing_tr_() {
		exit_equence__daptation__ayer__nalyzing();
		enter_equence__daptation__ayer__lanning_default();
	}
	
	private void effect__daptation__ayer__lanning_tr_() {
		exit_equence__daptation__ayer__lanning();
		enter_equence__daptation__ayer__xecuting_default();
	}
	
	private void effect__daptation__ayer__xecuting_tr_() {
		exit_equence__daptation__ayer__xecuting();
		enter_equence__daptation__ayer__onitoring_default();
	}
	
	private void effect__eta_daptation__ayer__onitoring_tr_() {
		exit_equence__eta_daptation__ayer__onitoring();
		enter_equence__eta_daptation__ayer__nalyzing_default();
	}
	
	private void effect__eta_daptation__ayer__nalyzing_tr_() {
		exit_equence__eta_daptation__ayer__nalyzing();
		enter_equence__eta_daptation__ayer__lanning_default();
	}
	
	private void effect__eta_daptation__ayer__lanning_tr_() {
		exit_equence__eta_daptation__ayer__lanning();
		enter_equence__eta_daptation__ayer__xecuting_default();
	}
	
	private void effect__eta_daptation__ayer__xecuting_tr_() {
		exit_equence__eta_daptation__ayer__xecuting();
		enter_equence__eta_daptation__ayer__onitoring_default();
	}
	
	/* Entry action for state 'Handling_Call_Config'. */
	private void entry_ction__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig() {
		if (____hone.getConfig__()==false) {
			____dapt_layer.raiseAdapt_need();
		}
	}
	
	/* Entry action for state 'Apply_changes'. */
	private void entry_ction__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes() {
		____hone.operationCallback.apply_hanges();
	}
	
	/* Entry action for state 'Active Call'. */
	private void entry_ction__ystem__ayer__ctive__all() {
		timer.setTimer(this, 0, 1 * 1000, true);
	}
	
	/* Entry action for state 'Dismiss Call'. */
	private void entry_ction__ystem__ayer__ismiss__all() {
		timer.setTimer(this, 1, 2 * 1000, false);
	}
	
	/* Entry action for state 'Apply_changes'. */
	private void entry_ction__daptation__ayer__onitoring___onitoring__egion__pply_changes() {
		____dapt_layer.operationCallback.apply_hanges();
	}
	
	/* Entry action for state 'Executing'. */
	private void entry_ction__daptation__ayer__xecuting() {
		____hone.setChanges(true);
		
		____hone.raiseApply_adapt();
	}
	
	/* Entry action for state 'Executing'. */
	private void entry_ction__eta_daptation__ayer__xecuting() {
		____dapt_layer.setChanges(true);
		
		____dapt_layer.raiseApply_adapt();
	}
	
	/* Exit action for state 'Apply_changes'. */
	private void exit_ction__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes() {
		____hone.setChanges(false);
		
		____hone.setConfig__(true);
	}
	
	/* Exit action for state 'Active Call'. */
	private void exit_ction__ystem__ayer__ctive__all() {
		timer.unsetTimer(this, 0);
	}
	
	/* Exit action for state 'Dismiss Call'. */
	private void exit_ction__ystem__ayer__ismiss__all() {
		timer.unsetTimer(this, 1);
		
		____hone.setCall_duration(0);
	}
	
	/* Exit action for state 'Apply_changes'. */
	private void exit_ction__daptation__ayer__onitoring___onitoring__egion__pply_changes() {
		____dapt_layer.setChanges(false);
		
		____dapt_layer.setConfig__(true);
	}
	
	/* 'default' enter sequence for state Idle */
	private void enter_equence__ystem__ayer__dle_default() {
		nextStateIndex = 0;
		stateVector[0] = State._ystem__ayer__dle;
	}
	
	/* 'default' enter sequence for state Incoming Call */
	private void enter_equence__ystem__ayer__ncoming__all_default() {
		enter_equence__ystem__ayer__ncoming__all___tate_ncoming__egion_default();
	}
	
	/* 'default' enter sequence for state Handling_Call_Config */
	private void enter_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig_default() {
		entry_ction__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig();
		nextStateIndex = 0;
		stateVector[0] = State._ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig;
	}
	
	/* 'default' enter sequence for state Apply_changes */
	private void enter_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes_default() {
		entry_ction__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes();
		nextStateIndex = 0;
		stateVector[0] = State._ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes;
	}
	
	/* 'default' enter sequence for state Active Call */
	private void enter_equence__ystem__ayer__ctive__all_default() {
		entry_ction__ystem__ayer__ctive__all();
		nextStateIndex = 0;
		stateVector[0] = State._ystem__ayer__ctive__all;
	}
	
	/* 'default' enter sequence for state Dismiss Call */
	private void enter_equence__ystem__ayer__ismiss__all_default() {
		entry_ction__ystem__ayer__ismiss__all();
		nextStateIndex = 0;
		stateVector[0] = State._ystem__ayer__ismiss__all;
	}
	
	/* 'default' enter sequence for state Process_Call */
	private void enter_equence__ystem__ayer__rocess__all_default() {
		nextStateIndex = 0;
		stateVector[0] = State._ystem__ayer__rocess__all;
	}
	
	/* 'default' enter sequence for state Monitoring */
	private void enter_equence__daptation__ayer__onitoring_default() {
		enter_equence__daptation__ayer__onitoring___onitoring__egion_default();
	}
	
	/* 'default' enter sequence for state Handling_Adapt_Config */
	private void enter_equence__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig_default() {
		nextStateIndex = 1;
		stateVector[1] = State._daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig;
	}
	
	/* 'default' enter sequence for state Apply_changes */
	private void enter_equence__daptation__ayer__onitoring___onitoring__egion__pply_changes_default() {
		entry_ction__daptation__ayer__onitoring___onitoring__egion__pply_changes();
		nextStateIndex = 1;
		stateVector[1] = State._daptation__ayer__onitoring___onitoring__egion__pply_changes;
	}
	
	/* 'default' enter sequence for state Analyzing */
	private void enter_equence__daptation__ayer__nalyzing_default() {
		nextStateIndex = 1;
		stateVector[1] = State._daptation__ayer__nalyzing;
	}
	
	/* 'default' enter sequence for state Planning */
	private void enter_equence__daptation__ayer__lanning_default() {
		nextStateIndex = 1;
		stateVector[1] = State._daptation__ayer__lanning;
	}
	
	/* 'default' enter sequence for state Executing */
	private void enter_equence__daptation__ayer__xecuting_default() {
		entry_ction__daptation__ayer__xecuting();
		nextStateIndex = 1;
		stateVector[1] = State._daptation__ayer__xecuting;
	}
	
	/* 'default' enter sequence for state Monitoring */
	private void enter_equence__eta_daptation__ayer__onitoring_default() {
		nextStateIndex = 2;
		stateVector[2] = State._eta_daptation__ayer__onitoring;
	}
	
	/* 'default' enter sequence for state Analyzing */
	private void enter_equence__eta_daptation__ayer__nalyzing_default() {
		nextStateIndex = 2;
		stateVector[2] = State._eta_daptation__ayer__nalyzing;
	}
	
	/* 'default' enter sequence for state Planning */
	private void enter_equence__eta_daptation__ayer__lanning_default() {
		nextStateIndex = 2;
		stateVector[2] = State._eta_daptation__ayer__lanning;
	}
	
	/* 'default' enter sequence for state Executing */
	private void enter_equence__eta_daptation__ayer__xecuting_default() {
		entry_ction__eta_daptation__ayer__xecuting();
		nextStateIndex = 2;
		stateVector[2] = State._eta_daptation__ayer__xecuting;
	}
	
	/* 'default' enter sequence for region System_Layer */
	private void enter_equence__ystem__ayer_default() {
		react__ystem__ayer__entry__efault();
	}
	
	/* 'default' enter sequence for region _StateIncoming_Region */
	private void enter_equence__ystem__ayer__ncoming__all___tate_ncoming__egion_default() {
		react__ystem__ayer__ncoming__all___tate_ncoming__egion__entry__efault();
	}
	
	/* 'default' enter sequence for region Adaptation_Layer */
	private void enter_equence__daptation__ayer_default() {
		react__daptation__ayer__entry__efault();
	}
	
	/* 'default' enter sequence for region _Monitoring_Region */
	private void enter_equence__daptation__ayer__onitoring___onitoring__egion_default() {
		react__daptation__ayer__onitoring___onitoring__egion__entry__efault();
	}
	
	/* 'default' enter sequence for region MetaAdaptation_Layer */
	private void enter_equence__eta_daptation__ayer_default() {
		react__eta_daptation__ayer__entry__efault();
	}
	
	/* Default exit sequence for state Idle */
	private void exit_equence__ystem__ayer__dle() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Incoming Call */
	private void exit_equence__ystem__ayer__ncoming__all() {
		exit_equence__ystem__ayer__ncoming__all___tate_ncoming__egion();
	}
	
	/* Default exit sequence for state Handling_Call_Config */
	private void exit_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Apply_changes */
	private void exit_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exit_ction__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes();
	}
	
	/* Default exit sequence for state Active Call */
	private void exit_equence__ystem__ayer__ctive__all() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exit_ction__ystem__ayer__ctive__all();
	}
	
	/* Default exit sequence for state Dismiss Call */
	private void exit_equence__ystem__ayer__ismiss__all() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exit_ction__ystem__ayer__ismiss__all();
	}
	
	/* Default exit sequence for state Process_Call */
	private void exit_equence__ystem__ayer__rocess__all() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Monitoring */
	private void exit_equence__daptation__ayer__onitoring() {
		exit_equence__daptation__ayer__onitoring___onitoring__egion();
	}
	
	/* Default exit sequence for state Handling_Adapt_Config */
	private void exit_equence__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for state Apply_changes */
	private void exit_equence__daptation__ayer__onitoring___onitoring__egion__pply_changes() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
		
		exit_ction__daptation__ayer__onitoring___onitoring__egion__pply_changes();
	}
	
	/* Default exit sequence for state Analyzing */
	private void exit_equence__daptation__ayer__nalyzing() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for state Planning */
	private void exit_equence__daptation__ayer__lanning() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for state Executing */
	private void exit_equence__daptation__ayer__xecuting() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for state Monitoring */
	private void exit_equence__eta_daptation__ayer__onitoring() {
		nextStateIndex = 2;
		stateVector[2] = State.$NullState$;
	}
	
	/* Default exit sequence for state Analyzing */
	private void exit_equence__eta_daptation__ayer__nalyzing() {
		nextStateIndex = 2;
		stateVector[2] = State.$NullState$;
	}
	
	/* Default exit sequence for state Planning */
	private void exit_equence__eta_daptation__ayer__lanning() {
		nextStateIndex = 2;
		stateVector[2] = State.$NullState$;
	}
	
	/* Default exit sequence for state Executing */
	private void exit_equence__eta_daptation__ayer__xecuting() {
		nextStateIndex = 2;
		stateVector[2] = State.$NullState$;
	}
	
	/* Default exit sequence for region System_Layer */
	private void exit_equence__ystem__ayer() {
		switch (stateVector[0]) {
		case _ystem__ayer__dle:
			exit_equence__ystem__ayer__dle();
			break;
		case _ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig:
			exit_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig();
			break;
		case _ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes:
			exit_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes();
			break;
		case _ystem__ayer__ctive__all:
			exit_equence__ystem__ayer__ctive__all();
			break;
		case _ystem__ayer__ismiss__all:
			exit_equence__ystem__ayer__ismiss__all();
			break;
		case _ystem__ayer__rocess__all:
			exit_equence__ystem__ayer__rocess__all();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region _StateIncoming_Region */
	private void exit_equence__ystem__ayer__ncoming__all___tate_ncoming__egion() {
		switch (stateVector[0]) {
		case _ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig:
			exit_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig();
			break;
		case _ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes:
			exit_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region Adaptation_Layer */
	private void exit_equence__daptation__ayer() {
		switch (stateVector[1]) {
		case _daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig:
			exit_equence__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig();
			break;
		case _daptation__ayer__onitoring___onitoring__egion__pply_changes:
			exit_equence__daptation__ayer__onitoring___onitoring__egion__pply_changes();
			break;
		case _daptation__ayer__nalyzing:
			exit_equence__daptation__ayer__nalyzing();
			break;
		case _daptation__ayer__lanning:
			exit_equence__daptation__ayer__lanning();
			break;
		case _daptation__ayer__xecuting:
			exit_equence__daptation__ayer__xecuting();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region _Monitoring_Region */
	private void exit_equence__daptation__ayer__onitoring___onitoring__egion() {
		switch (stateVector[1]) {
		case _daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig:
			exit_equence__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig();
			break;
		case _daptation__ayer__onitoring___onitoring__egion__pply_changes:
			exit_equence__daptation__ayer__onitoring___onitoring__egion__pply_changes();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region MetaAdaptation_Layer */
	private void exit_equence__eta_daptation__ayer() {
		switch (stateVector[2]) {
		case _eta_daptation__ayer__onitoring:
			exit_equence__eta_daptation__ayer__onitoring();
			break;
		case _eta_daptation__ayer__nalyzing:
			exit_equence__eta_daptation__ayer__nalyzing();
			break;
		case _eta_daptation__ayer__lanning:
			exit_equence__eta_daptation__ayer__lanning();
			break;
		case _eta_daptation__ayer__xecuting:
			exit_equence__eta_daptation__ayer__xecuting();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state Idle. */
	private void react__ystem__ayer__dle() {
		if (check__ystem__ayer__dle_tr__tr_()) {
			effect__ystem__ayer__dle_tr_();
		}
	}
	
	/* The reactions of state Handling_Call_Config. */
	private void react__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig() {
		if (check__ystem__ayer__ncoming__all_tr__tr_()) {
			effect__ystem__ayer__ncoming__all_tr_();
		} else {
			if (check__ystem__ayer_check__ystem__ayer__ncoming__all_tr__tr_()) {
				effect__ystem__ayer_effect__ystem__ayer__ncoming__all_tr_();
			} else {
				if (check__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig_tr__tr_()) {
					effect__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig_tr_();
				}
			}
		}
	}
	
	/* The reactions of state Apply_changes. */
	private void react__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes() {
		if (check__ystem__ayer__ncoming__all_tr__tr_()) {
			effect__ystem__ayer__ncoming__all_tr_();
		} else {
			if (check__ystem__ayer_check__ystem__ayer__ncoming__all_tr__tr_()) {
				effect__ystem__ayer_effect__ystem__ayer__ncoming__all_tr_();
			} else {
				effect__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes_tr_();
			}
		}
	}
	
	/* The reactions of state Active Call. */
	private void react__ystem__ayer__ctive__all() {
		if (check__ystem__ayer__ctive__all_tr__tr_()) {
			effect__ystem__ayer__ctive__all_tr_();
		} else {
			if (check__ystem__ayer__ctive__all_lr__lr_()) {
				effect__ystem__ayer__ctive__all_lr__lr_();
			}
		}
	}
	
	/* The reactions of state Dismiss Call. */
	private void react__ystem__ayer__ismiss__all() {
		if (check__ystem__ayer__ismiss__all_tr__tr_()) {
			effect__ystem__ayer__ismiss__all_tr_();
		}
	}
	
	/* The reactions of state Process_Call. */
	private void react__ystem__ayer__rocess__all() {
		if (check__ystem__ayer__rocess__all_tr__tr_()) {
			effect__ystem__ayer__rocess__all_tr_();
		} else {
			if (check__ystem__ayer_check__ystem__ayer__rocess__all_tr__tr_()) {
				effect__ystem__ayer_effect__ystem__ayer__rocess__all_tr_();
			}
		}
	}
	
	/* The reactions of state Handling_Adapt_Config. */
	private void react__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig() {
		if (check__daptation__ayer__onitoring_tr__tr_()) {
			effect__daptation__ayer__onitoring_tr_();
		} else {
			if (check__daptation__ayer_check__daptation__ayer__onitoring_tr__tr_()) {
				effect__daptation__ayer_effect__daptation__ayer__onitoring_tr_();
			} else {
				if (check__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig_tr__tr_()) {
					effect__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig_tr_();
				} else {
					if (check__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig_lr__lr_()) {
						effect__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig_lr__lr_();
					}
				}
			}
		}
	}
	
	/* The reactions of state Apply_changes. */
	private void react__daptation__ayer__onitoring___onitoring__egion__pply_changes() {
		if (check__daptation__ayer__onitoring_tr__tr_()) {
			effect__daptation__ayer__onitoring_tr_();
		} else {
			if (check__daptation__ayer_check__daptation__ayer__onitoring_tr__tr_()) {
				effect__daptation__ayer_effect__daptation__ayer__onitoring_tr_();
			} else {
				effect__daptation__ayer__onitoring___onitoring__egion__pply_changes_tr_();
			}
		}
	}
	
	/* The reactions of state Analyzing. */
	private void react__daptation__ayer__nalyzing() {
		effect__daptation__ayer__nalyzing_tr_();
	}
	
	/* The reactions of state Planning. */
	private void react__daptation__ayer__lanning() {
		effect__daptation__ayer__lanning_tr_();
	}
	
	/* The reactions of state Executing. */
	private void react__daptation__ayer__xecuting() {
		effect__daptation__ayer__xecuting_tr_();
	}
	
	/* The reactions of state Monitoring. */
	private void react__eta_daptation__ayer__onitoring() {
		if (check__eta_daptation__ayer__onitoring_tr__tr_()) {
			effect__eta_daptation__ayer__onitoring_tr_();
		}
	}
	
	/* The reactions of state Analyzing. */
	private void react__eta_daptation__ayer__nalyzing() {
		effect__eta_daptation__ayer__nalyzing_tr_();
	}
	
	/* The reactions of state Planning. */
	private void react__eta_daptation__ayer__lanning() {
		effect__eta_daptation__ayer__lanning_tr_();
	}
	
	/* The reactions of state Executing. */
	private void react__eta_daptation__ayer__xecuting() {
		effect__eta_daptation__ayer__xecuting_tr_();
	}
	
	/* Default react sequence for initial entry  */
	private void react__ystem__ayer__entry__efault() {
		enter_equence__ystem__ayer__dle_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react__ystem__ayer__ncoming__all___tate_ncoming__egion__entry__efault() {
		enter_equence__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react__daptation__ayer__onitoring___onitoring__egion__entry__efault() {
		enter_equence__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react__daptation__ayer__entry__efault() {
		enter_equence__daptation__ayer__onitoring_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react__eta_daptation__ayer__entry__efault() {
		enter_equence__eta_daptation__ayer__onitoring_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case _ystem__ayer__dle:
				react__ystem__ayer__dle();
				break;
			case _ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig:
				react__ystem__ayer__ncoming__all___tate_ncoming__egion__andling__all__onfig();
				break;
			case _ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes:
				react__ystem__ayer__ncoming__all___tate_ncoming__egion__pply_changes();
				break;
			case _ystem__ayer__ctive__all:
				react__ystem__ayer__ctive__all();
				break;
			case _ystem__ayer__ismiss__all:
				react__ystem__ayer__ismiss__all();
				break;
			case _ystem__ayer__rocess__all:
				react__ystem__ayer__rocess__all();
				break;
			case _daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig:
				react__daptation__ayer__onitoring___onitoring__egion__andling__dapt__onfig();
				break;
			case _daptation__ayer__onitoring___onitoring__egion__pply_changes:
				react__daptation__ayer__onitoring___onitoring__egion__pply_changes();
				break;
			case _daptation__ayer__nalyzing:
				react__daptation__ayer__nalyzing();
				break;
			case _daptation__ayer__lanning:
				react__daptation__ayer__lanning();
				break;
			case _daptation__ayer__xecuting:
				react__daptation__ayer__xecuting();
				break;
			case _eta_daptation__ayer__onitoring:
				react__eta_daptation__ayer__onitoring();
				break;
			case _eta_daptation__ayer__nalyzing:
				react__eta_daptation__ayer__nalyzing();
				break;
			case _eta_daptation__ayer__lanning:
				react__eta_daptation__ayer__lanning();
				break;
			case _eta_daptation__ayer__xecuting:
				react__eta_daptation__ayer__xecuting();
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
}
