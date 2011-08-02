import java.util.Comparator;


public class Index implements Comparable{
Integer start;
Integer end;
public Integer getStart() {
	return start;
}
public void setStart(Integer start) {
	this.start = start;
}
public Integer getEnd() {
	return end;
}
public void setEnd(Integer end) {
	this.end = end;
}


public int compareTo(Object index1) {
  
	index1 = (Index) index1;
	Index index2 = (Index) this;
	Integer  index1Distance = ((Index) index1).getEnd().intValue() -  ((Index) index1).getStart().intValue();
	Integer index2Distance = ((Index) index2).getEnd().intValue() -  ((Index) index2).getStart().intValue();
	if (index1Distance > index2Distance)
		return 1;
	else if (index1Distance == index2Distance)
		return 0;
	else 
		return -1;
}


}
