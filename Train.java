public class Train {

	// Kann für die Ausgabe verwendet werden
	private static final String LOCOMOTIVE = "<___|o|";

	private Waggon head;

	public int getSize() {
		Waggon current = head;
		int iterator = 0;
		
		while (current != null) {
			current = current.getNext();
			iterator++;
		}
		
		return iterator;
	}

	public int getPassengerCount() {
		Waggon current = head;
		int sum = 0;
		
		while (current != null) {
			sum = sum + current.getPassengers();
			current = current.getNext();
		}
		
		return sum;
	}

	public int getCapacity() {
		Waggon current = head;
		int sum = 0;
		
		while (current != null) {
			sum = sum + current.getCapacity();
			current = current.getNext();
		}
		
		return sum;
	}

	public void appendWaggon(Waggon waggon) {
		if (head == null) {
			head = waggon;
			return;
		}

		Waggon current = head;
		
		while (current.getNext() != null) {
			current = current.getNext();
		}
		
		current.setNext(waggon);
		current.getNext().setNext(null);
	}

	public void boardPassengers(int numberOfPassengers) {
		Waggon current = head;
		int space;
		
		while (current != null && numberOfPassengers != 0) {
			space = current.getCapacity() - current.getPassengers();
			
			if(space > numberOfPassengers) {
				current.setPassengers(current.getPassengers() + numberOfPassengers);
				return;
			} else {
				current.setPassengers(current.getCapacity());
				numberOfPassengers = numberOfPassengers - space;
			}

			current = current.getNext();
		}
	}

	public Train uncoupleWaggons(int index) {
		if (index <= 0 || index >= this.getSize())
			return null;
		
		Waggon current = head;
		int iterator = 1;
		
		while (current != null && iterator < index) {
			current = current.getNext();
			iterator++;
		}
		
		Train newTr = new Train();
		newTr.head = current.getNext();
		
		current.setNext(null);
		
		return newTr;
	}

	public void insertWaggon(Waggon waggon, int index) {
		if (index < 0)
			return;
		
		if (head == null) {
			head = waggon;
			return;
		}
				
		Waggon current = head;
		int iterator = 0;
		
		if (index >= this.getSize()) {
			if (head.getNext() == null) {
				head.setNext(waggon);
				waggon.setNext(null);
				return;
			}
			
			while (current.getNext() != null) {
				current = current.getNext();
			}
			
			current.setNext(waggon);
			waggon.setNext(null);
			return;
		}
		
		if (index == 0) {
			waggon.setNext(head);
			head = waggon;
			return;
		}
		
		while (current != null && iterator < index - 1) {
			current = current.getNext();
			iterator++;
		}
		
		waggon.setNext(current.getNext());
		current.setNext(waggon);
	}

	public void turnOver() {
		Waggon[] aPrev = new Waggon[this.getSize()];

		Waggon cur  = head;
		int i = 0;
		
		while (cur != null) {
			aPrev[i] = cur;
			cur = cur.getNext();
			i++;
		}
		
		head = aPrev[aPrev.length-1];
		aPrev[0].setNext(null);
		
		for (int j=1; j<aPrev.length; j++) {
			aPrev[j].setNext(aPrev[j-1]);
		}
	}

	public void removeWaggon(Waggon waggon) {
		if (head == null) 
			return;
		
		if (this.getSize() == 1) {
			if (head == waggon) {
				head = null;
			}
			return;
		}
		
		if (head == waggon) {
			head = head.getNext();
			return;
		}
		
		Waggon cur = head;
		
		while (cur.getNext() != null) {
			if (cur.getNext() == waggon) {
				if (cur.getNext().getNext() != null) {
					cur.setNext(cur.getNext().getNext());
				} else {
					cur.setNext(null);
				}
				return;
			}
			
			cur = cur.getNext();
		}
	}

	public Waggon getWaggonAt(int index) {
		if (this.getSize() <= index) 
			return null;
	
		int iterator = 0;
		Waggon cur = head;
		
		while (cur != null && iterator < index) {
			cur = cur.getNext();
			iterator++;
		}
		
		return cur;
	}

	@Override
	public String toString() {
		if (getSize() == 0) {
			return LOCOMOTIVE;
		}

		StringBuilder str = new StringBuilder(LOCOMOTIVE);

		Waggon pointer = head;
		while (pointer != null) {
			str.append(" <---> ");
			str.append(pointer.getName());
			pointer = pointer.getNext();
		}

		return str.toString();
	}

}
