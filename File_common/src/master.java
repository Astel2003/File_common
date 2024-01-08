import java.io.File;
import java.util.Scanner;

public class master {

	final static String string_enter_key ="\n";
	
	public static void main(String[] args) {
		// funtion_name 어레이 수정 후 고칠 것. 현재는 폴더명 변환(작품명,작가,번호순)기능만 구현
		/*
		String master_messege = "파일관리를 실행합니다.";
		String select_message = "실행코자 하는 기능을 선택하십시오.";
		ArrayList<String> funtion_name = new ArrayList<String>();
		
		int function_index = 1;
		funtion_name.add("폴더명 변환(작품명,작가,번호순)");
		funtion_name.add("폴더명 변환(작가 제거)");
		
		System.out.println(master_messege+string_enter_key+select_message);
		for (String function_name_string : funtion_name) {
			System.out.println(function_index+"."+function_name_string);
			function_index++;
		}
		Scanner sc = init_scanner();
		System.out.print(">");
		int function_code =sc.nextInt();
		*/
		System.out.println("폴더명 변환(작품명,작가,번호순)기능 실행\n경로응 입력하세요.");
		System.out.print(">");
		Scanner sc = init_scanner();
		String path = sc.nextLine();
		
		System.out.println(path);
		
		//convert_folder_name_workname_artist_number(path);
		convert_folder_name_remove_date_number(path);
		
		System.out.println("작업완료");
	}
	
	static Scanner init_scanner() {
		Scanner sc = new Scanner(System.in);
		return sc;
	}
	//나머지 스트링 회귀
	static String etc_name(String temp_list_name, String start, String end) {
		temp_list_name = 
				temp_list_name.substring(0,temp_list_name.indexOf(start))+
				temp_list_name.substring(temp_list_name.indexOf(end)+1)
				;
		return temp_list_name.trim();
	}
	// 폴더명 변환(작품명,작가,번호순)
	static void convert_folder_name_workname_artist_number(String path) {
		File file = new File(path);
		String artist_start = "[";
		String artist_end = "]";
//		String number_start = "(";
//		String number_end = ")";
		
		String[] list =file.list();
		for(String list_name : list) {
			//다음 문장은 테스트용
			//String list_name = list[0];
			
			String temp_list_name = list_name;
			String artist =  temp_list_name.substring( temp_list_name.indexOf(artist_start),  temp_list_name.indexOf(artist_end)+1);
			temp_list_name = etc_name(temp_list_name,artist_start,artist_end);
//			String number =  temp_list_name.substring( temp_list_name.indexOf(number_start),  temp_list_name.indexOf(number_end)+1);
//			temp_list_name = etc_name(temp_list_name,number_start,number_end);
			//System.out.println(artist);
			//System.out.println(number);
			//System.out.println(temp_list_name);
			//System.out.println(list_name);
			
			String new_name = temp_list_name+" "+ artist;
			//String new_name = artist +" "+ temp_list_name;
			
			//System.out.println(new_name);
			File target = new File(path+"\\"+list_name);
			File dest = new File(path+"\\"+new_name);
			target.renameTo(dest);
		}
	}
	// 폴더명 변환(작가제거)
	static void convert_folder_name_remove_artist__workname_number(String path) {
		File file = new File(path);
		String artist_start = "[";
		String artist_end = "]";
		String number_start = "(";
		String number_end = ")";
		
		String[] list =file.list();
		for(String list_name : list) {
			//다음 문장은 테스트용
			//String list_name = list[0];
			
			String temp_list_name = list_name;
			
			int dumi = temp_list_name.indexOf(artist_start);
			String artist =  temp_list_name.substring(temp_list_name.indexOf(artist_start),  temp_list_name.indexOf(artist_end)+1);
			temp_list_name = etc_name(temp_list_name,artist_start,artist_end);
//			String number =  temp_list_name.substring( temp_list_name.indexOf(number_start),  temp_list_name.indexOf(number_end)+1);
//			temp_list_name = etc_name(temp_list_name,number_start,number_end);
			
			//String new_name = temp_list_name+" "+ number+" "+ artist;
			//String new_name = temp_list_name+" "+ number;
			
			File target = new File(path+"\\"+list_name);
			File dest = new File(path+"\\"+temp_list_name);
			target.renameTo(dest);
		}
	}

	static void convert_folder_name_remove_date_number(String path) {
		File file = new File(path);
		String artist_end = "]";
		String dest_path = null;
		
		for(int i = 0; i <10; i++) {
			String i_str = String.valueOf(i);
			File folder = new File(path+"\\rename_"+i_str);
			if(!folder.exists()) {
				folder.mkdir();
				dest_path = folder.getPath();
				break;
			}
		}
		if(dest_path == null)
		{
			System.out.println("폴더 생성 실패");
			return;
		}
		String[] list =file.list();
		for(String list_name : list) {
			File target = new File(path+"\\"+list_name);
			if(!target.isDirectory()) {
				String temp_list_name = list_name;
				temp_list_name = temp_list_name.substring(temp_list_name.indexOf(artist_end)+1,temp_list_name.length());
	
				File dest = new File(dest_path+"\\"+temp_list_name);
				
				target.renameTo(dest);
			}
		}
	}
}
