package util;

import java.util.Comparator;

import Organisms.Organism;

public class OrganismSorter implements Comparator<Organism> {

	    @Override
	    public int compare(Organism org1, Organism org2) {
	    	if(org1.getEffort() > (org2.getEffort()))
	    		return 1;
	    	else if (org1.getEffort() < (org2.getEffort()))
	    		return -1;
	    	else
	    	{
	    		if(org1.getAge() > (org2.getAge()))
		    		return 1;
		    	else if (org1.getAge() < (org2.getAge()))
		    		return -1;
		    	else return 0;
	    	}
	    }
	
}
