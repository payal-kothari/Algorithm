import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;



public class Register {

	static ArrayList<Student> allStudents = new ArrayList<>();
	static ArrayList<Course> allCourses = new ArrayList<>();
	static int count = 0;
	static int[][] edges;
	static int studLimit  = 3;
	static int TotalCount = 0;
	static int TotalCourses;
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		String line = input.nextLine();
		String[] line1 = line.split(" ");
		int TotalStudents = Integer.parseInt(line1[0]);
		TotalCourses = Integer.parseInt(line1[1]);
		
		for(int i=0;i<TotalCourses;i++){					// store information of the courses
			Course co = new Course();
			co.CourseID = i+1;
			allCourses.add(co);								// store course objects in the array
		}
		
		for(int i=0;i<TotalStudents;i++){					// store information of the students
			Student st = new Student();
			st.StudentID = i+1;
			String temp = input.nextLine();
			String[] temp1 = temp.split(" ");
			for(int j=0;j<temp1.length;j++){
				st.ConnectedCoureses.add(Integer.parseInt(temp1[j]));		// maintain info of courses for the student
				allCourses.get(Integer.parseInt(temp1[j]) -1).student.add(st.StudentID);  // maintain info of students for the course
			}
			allStudents.add(st);						  // store student objects in the array
		}
		
		for(int i=0;i<TotalCourses;i++){
			allCourses.get(i).maxAllowed = input.nextInt();	// store max limit of the courses
		}
		
		edges = new int[TotalCourses + TotalStudents + 1][TotalCourses + TotalStudents + 1];   // matrix to store edges between vertices
		int TotalVertices = TotalCourses + TotalStudents +1;
		int source = 0;											// add source
		int sink = TotalCourses + TotalStudents ;				// add sink
		
		for(int i=0; i<allStudents.size(); i++){				// update the matrix 
			ArrayList<Integer> temp = new ArrayList<>();
			temp = allStudents.get(i).ConnectedCoureses;
			for(int j=0; j<temp.size(); j++){
				int studIndex = allStudents.get(i).StudentID -1;
				int courseIndex = temp.get(j) + TotalStudents -1;
				edges[studIndex][courseIndex] = 1;
			}
		}
		

		for(int i =0 ; i<allCourses.size(); i++){				// update the matrix
			int courseIndex = allCourses.get(i).CourseID + TotalStudents -1;
			edges[courseIndex][sink] = allCourses.get(i).maxAllowed;
		}
		
		int done =0;
		for(int i =0; i< allStudents.size(); i++){					// i is the student index in allStudent array
			done = 0;
			ArrayList<Integer> temp = new ArrayList<>();
			temp = allStudents.get(i).ConnectedCoureses;
			int alreadyRegistered = allStudents.get(i).hasApplied;
				for(int j = 0; j<temp.size(); j++){
					if(alreadyRegistered < studLimit){				// a student should only register 3 courses
						int studIndex = allStudents.get(i).StudentID -1;
						int courseIndex = temp.get(j) + TotalStudents -1;
						if(edges[courseIndex][sink] == 0){
							boolean res = findAlternate(courseIndex,studIndex,TotalStudents);  // find augmented path
							if(res == true && edges[courseIndex][sink] != 0){
								edges[studIndex][courseIndex] = 0;
								edges[courseIndex][studIndex] = 1;
								edges[courseIndex][sink]--;
								TotalCount++;
								allStudents.get(i).hasApplied++;
							    alreadyRegistered++;
							}
						}
						else if(edges[courseIndex][sink] != 0 && edges[studIndex][courseIndex] != 0){ // assign course to student if available
								edges[studIndex][courseIndex] = 0;
								edges[courseIndex][studIndex] = 1;
								edges[courseIndex][sink]--;
								TotalCount++;
								allStudents.get(i).hasApplied++;
							    alreadyRegistered++;
						}
					}
				}
		}
		
		System.out.println(TotalCount);									// answer
		
	}

	/**
	 * 
	 * This method finds the augmented path
	 * 
	 * @param courseIndex
	 * @param studIndex
	 * @param TotalStudents
	 * @return true/false
	 */
	private static boolean findAlternate(int courseIndex,int studIndex,int TotalStudents) {
		
		int tempCourseIndex = courseIndex - TotalStudents ;
		ArrayList<Integer> tempStudents = new ArrayList<>();
		tempStudents = allCourses.get(tempCourseIndex).student;
		
		for(int i=0; i<tempStudents.size(); i++){
			int tempStudIndex = tempStudents.get(i) -1;
			if(edges[courseIndex][tempStudIndex] == 1){						// check if backward edge is present
				boolean result = tryThis(tempStudIndex,TotalStudents);      // try to find alternate path
				if(result == true){											// if alternate path found
					edges[courseIndex][tempStudIndex] --;
					edges[tempStudIndex][courseIndex] ++;
					edges[courseIndex][TotalCourses + TotalStudents]++;
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	/**
	 * 
	 * Method tries to find an alternate path
	 * 
	 * @param tempStudIndex
	 * @param TotalStudents
	 * @return true/false
	 */

	private static boolean tryThis(int tempStudIndex,int TotalStudents) {
		ArrayList<Integer> temp = new ArrayList<>();
		temp = allStudents.get(tempStudIndex).ConnectedCoureses;
		
		for(int i=0; i<temp.size(); i++){
			int courseIndex = temp.get(i) + TotalStudents -1;
			if(edges[courseIndex][TotalCourses + TotalStudents] != 0 && edges[tempStudIndex][courseIndex] != 0){  // if alternate path exists
				edges[tempStudIndex][courseIndex] = 0;
				edges[courseIndex][tempStudIndex] = 1;
				edges[courseIndex][TotalCourses + TotalStudents]--;
				return true;
			}
		}
		
		return false;
	}
}

/**
 * 
 * This class stores the information of the courses
 * 
 * 
 *
 */

class Course {
	int CourseID;
	int maxAllowed;
	ArrayList<Integer> student = new ArrayList<>();

}

/**
 * 
 * This class stores the information of the students
 * 
 *
 */
class Student{
	int StudentID;
	int hasApplied;
	ArrayList<Integer> ConnectedCoureses = new ArrayList<>();
	
}