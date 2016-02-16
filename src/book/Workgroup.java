package book;

import java.util.HashSet;
import java.util.Set;

public class Workgroup {

	public Set<Person> memberSet = new HashSet<Person>();

	public Set<Person> getMemberSet() {
		return memberSet;
	}

	public void setMemberSet(Set<Person> memberSet) {
		this.memberSet = memberSet;
	}

	public void add(Person p) {
		memberSet.add(p);
	}

	public void remove(Person person) {
		memberSet.remove(person);
	}

}
