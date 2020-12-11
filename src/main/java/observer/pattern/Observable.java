package observer.pattern;

import java.util.ArrayList;
import java.util.Vector;

import observer.CourseRecord;

/**
 * An abstract class for all Observable subjects
 */
public abstract class Observable {
	/**
	 * Constructs an Observable object
	 */
	public Observable() {
		this.observers = new Vector<Observer>();
	}

	/**
	 * Attach to this Subject
	 * 
	 * @param o
	 *            the Observer that wishes to attach
	 */
	public void attach(Observer o, boolean isController) {
		if (isController)
			this.controller = o;
		else
			this.observers.addElement(o);
	}

	/**
	 * Detach from this Subject
	 * 
	 * @param o
	 *            the Observer that wishes to detach
	 */
	public void detach(Observer o) {
		for (int i = 0; i < observers.size(); i++) {
			if (observers.elementAt(i).equals(o))
				observers.removeElementAt(i);
		}
	}

	/**
	 * Notify all Observers that a subject has changed
	 * if notifyController is true,  the controller is also notified
	 * 
	 */
	public void notifyObservers(Vector<CourseRecord> courseData, boolean notifyController) {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = observers.elementAt(i);
			observer.update(this, courseData);
		}
		if (notifyController)
			controller.update(this, courseData);
	}

		/**
	 * Notify the observers that Subject has changed
	 * if notifyController is true, the controller is also notified
	 * It is a smart push
	 */
	public void notifyObservers(Vector<CourseRecord> courseData, ArrayList<Observer> observers, boolean notifyController){
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).update(this, courseData);
		}
		if (notifyController)
			controller.update(this, courseData);
	}

	/**
	 * Pull updated data from this Subject
	 * 
	 * @return the updated data from the Subject
	 */
	public abstract Object getUpdate();

	protected Vector<Observer> observers;

	protected Observer controller;
}