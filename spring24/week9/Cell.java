package spring24.week9;

/**
 * A Cell represents a single cell in the Sudoku grid.
 * This helper class is used to store the row, column, value, and possible values for a cell.
 * The possible values are stored in an ArrayList of Integers, and are sorted in ascending order.
 * 
 * The Cell class also contains a method to add a possible value to the cell, and a method to get the possible values.
 */
public class Cell {
    private int row;
    private int col;
    private int box;
    private int value;
    private List possibleValues;
    
    /**
     * Create a new Cell with the given row and column.
     * 
     * The value of the cell is initially -2, and the possible values are initially empty.
     * 
     * @param row
     * @param col
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        setBox();
        possibleValues = new List();
    }

    /**
     * Create a new Cell with the given row, column, and value.
     * 
     * The possible values are initially empty.
     * 
     * @param row
     * @param col
     * @param value
     */
    public Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
        setBox();
        possibleValues = new List();
    }

    /**
     * Create a new Cell with the given row, column, and possible values.
     * 
     * The value of the cell is initially -2.
     * 
     * @param row
     * @param col
     * @param possibleValues
     */
    public Cell(int row, int col, int[] possibleValues) {
        this.row = row;
        this.col = col;
        setBox();
        this.possibleValues = new List(possibleValues);
    }

    /**
     * Set the value of a cell.
     * 
     * The value must be between 1 and 9, inclusive.
     * 
     * @param value
     */
    public void setValue(int value) {
        possibleValues.clear();
        this.value = value;
    }

    /**
     * Get the value of a cell.
     * 
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * Get the box a cell is in.
     * 
     * @return box number
     */
    public int getBox() {
        return box;
    }

    /**
     * Add a value to the list of possible values.
     * 
     * If the value is already possible for the cell, or if the value is not a valid value for the cell, then the value is not added.
     * 
     * @param value
     */
    public void addPossibleValue(int value) {
        if(possibleValues.contains(value)) {
            return;
        } else if(value < 1 || value > 9) {
            return;
        }
        
        possibleValues.add(value);
    }

    /**
     * Set the list of possible values for the cell.
     * 
     * @param possibleValues
     */
    public void setPossibleValues(int[] possibleValues) {
        this.possibleValues = new List(possibleValues);
    }

    /**
     * Remove a value from the list of possible values.
     * 
     * @return
     */
    public void removePossibleValue(int value) {
        if(!possibleValues.contains(value)) {
            return;
        }
        
        possibleValues.remove(value);
    }
    
    /**
     * Get a list of possible values for the cell.
     * 
     * This list is a copy of the cell's possible values, and is sorted in ascending order.
     * 
     * @return list of possible values for the cell
     */
    public int[] getPossibleValues() {
        return possibleValues.toArray();
    }
    
    /**
     * Given the cell's row and column, set the box number for the cell.
     */
    private void setBox() {
        int boxRow = row / 3;
        int boxCol = col / 3;
        box = boxRow * 3 + boxCol;
    }
    
    /**
     * This class is used to store the possible values for a cell in a sorted list.
     * The list is sorted in ascending order, and the values are stored in a linked list.
     * 
     * The List class also contains a method to add a value to the list, and a method to get the list of values as an array.
     */
    private class List {

        private Value head;
        private Value tail;
        private int size;
        private int max;
        private int min;

        /**
         * Create a new empty List.
         * 
         * By default, the head and tail are null, the size is 0, and the max and min are 0.
         */
        public List() {
            head = null;
            tail = null;
            size = 0;
            max = 0;
            min = 0;
        }

        /**
         * Create a new List with the given initial value.
         * 
         * By default, the head and tail are the same, the size is 1, and the max and min are the initial value.
         * @param initValue
         */
        public List(int initValue) {
            head = new Value(initValue);
            tail = head;
            size = 1;
            max = initValue;
            min = initValue;
        }
        
        /**
         * Create a new List with the given initial values.
         * 
         * @param initValues
         */
        public List(int[] initValues) {
            for(int i = 0; i < initValues.length; i++) add(initValues[i]);
        }

        /**
         * Add a new value to the list.
         * 
         * By default, the value will be inserted at it's sorted place in the list.
         * 
         * @param newValue
         */
        public void add(int newValue) {
            if(size == 0) {
                head = new Value(newValue);
                tail = head;
                size = 1;
                max = newValue;
                min = newValue;
            } else {
                if(newValue > max) {
                    max = newValue;
                    tail.next = new Value(newValue);
                    tail = tail.next;
                } else if(newValue < min) {
                    min = newValue;
                    Value tmp = new Value(newValue);
                    tmp.setNext(head);
                    head = tmp;
                } else {
                    Value current = head;
                    while(current.next != null && current.next.value < newValue) {
                        current = current.next;
                    }
                    Value tmp = new Value(newValue);
                    tmp.setNext(current.next);
                    current.setNext(tmp);
                }
                size++;
            }
        }
        
        /**
         * Remove a value from the list.
         * 
         * @param value
         */
        public void remove(int value) {
            if(size == 0) {
                System.out.println("List is empty.");
                return;
            }

            if(head.value == value) {
                head = head.next;
                size--;
                return;
            }

            Value current = head;
            while(current.next != null) {
                if(current.next.value == value) {
                    current.setNext(current.next.next);
                    size--;
                    return;
                }
                current = current.next;
            }

            System.out.println("Value " + value + " is not in the list.");
        }

        /**
         * Get the list of values as an array.
         * 
         * @return array of values in the list
         */
        public int[] toArray() {
            int[] array = new int[size];
            Value current = head;
            for(int i = 0; i < size; i++) {
                array[i] = current.value;
                current = current.next;
            }
            return array;
        }

        /**
         * Check if the list contains the given value.
         * 
         * @param value
         * @return
         */
        public boolean contains(int value) {
            Value current = head;
            while(current != null) {
                if(current.value == value) return true;
                current = current.next;
            }
            return false;
        }

        /**
         * Clear the list of all values.
         */
        public void clear() {
            head = null;
            tail = null;
            size = 0;
            max = 0;
            min = 0;
        }

        /**
         * Value class is a Node for the List class.
         */
        private class Value {
            
            private int value;
            private Value next;
            
            public Value(int value) {
                this.value = value;
                next = null;
            }

            public void setNext(Value next) {
                this.next = next;
            }
        }
    }
}