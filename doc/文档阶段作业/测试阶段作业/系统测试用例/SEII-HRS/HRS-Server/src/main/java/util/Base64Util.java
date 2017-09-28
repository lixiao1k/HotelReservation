package util;


public class Base64Util {
	private static char[] data = {'A','B','C','D','E','F','G','H'
									,'I','J','K','L','M','N','O','P'
									,'Q','R','S','T','U','V','W','X'
									,'Y','Z','a','b','c','d','e','f'
									,'g','h','i','j','k','l','m','n'
									,'o','p','q','r','s','t','u','v'
									,'w','x','y','z','0','1','2','3'
									,'4','5','6','7','8','9','+','/'};
	private static int getPos(char c){
		for(int i=0;i<data.length;i++)
			if(data[i]==c)
				return i;
		return 0;
	}
	private static int bin2dec(String s){
		int x = 0;
		int pow = 0;
		int length = s.length();
		for (int i=length-1; i>=0; i--) {
			x += Math.pow(2, pow) * (s.charAt(i) == '1' ? 1 : 0);
			pow ++;
		}
		return x;
	}
	private static String span2six(String s){
		while(s.length()<6){
			s = '0'+s;
		}
		return s;
	}
	private static int[] transform(String s){
		String result = "";
		result += span2six(Integer.toBinaryString(getPos(s.charAt(0))&63));
		result += span2six(Integer.toBinaryString(getPos(s.charAt(1))&63));
		result += span2six(Integer.toBinaryString(getPos(s.charAt(2))&63));
		result += span2six(Integer.toBinaryString(getPos(s.charAt(3))&63));
		int[] bytes = new int[3];
		bytes[0] = bin2dec(result.substring(0, 8));
		bytes[1] = bin2dec(result.substring(8, 16));
		bytes[2] = bin2dec(result.substring(16, 24));
		return bytes;
	}
	public static String encode(String s){
		String result="";
		int pos = 0;
		int length = s.length();
		long delta = 63;
		while(pos<length){
			int flag = 0;
			long datacache = 0;
			datacache += s.charAt(pos)<<24;
			flag++;
			pos++;
			if(pos<length){
				datacache += s.charAt(pos)<<16;
				flag++;
				pos++;
			}
			if(pos<length){
				datacache += s.charAt(pos)<<8;
				flag++;
				pos++;
			}
			if (flag==3){
				result += data[(int) (datacache&(delta<<26))>>26];
				result += data[(int) (datacache&(delta<<20))>>20];
				result += data[(int) (datacache&(delta<<14))>>14];
				result += data[(int) (datacache&(delta<<8))>>8];
			}
			else if (flag==2){
				result += data[(int) (datacache&(delta<<26))>>26];
				result += data[(int) (datacache&(delta<<20))>>20];
				result += data[(int) (datacache&(delta<<14))>>14];
				result += '=';
			}
			else if (flag==1){
				result += data[(int) (datacache&(delta<<26))>>26];
				result += data[(int) (datacache&(delta<<20))>>20];
				result += "==";
			}
			
		}
		return result;
	}
	public static String decode(String s){
		String result = "";
		int length = s.length();
		for(int i=0;i<length/4-1;i++){
			String temp = s.substring(i*4,(i+1)*4);
			int[] bytes = transform(temp);
			result +=(char)bytes[0];
			result +=(char)bytes[1];
			result +=(char)bytes[2];
		}
		if(s.charAt(length-1)!='='){
			String temp = s.substring((length/4-1)*4,(length/4)*4);
			int[] bytes = transform(temp);
			result +=(char)bytes[0];
			result +=(char)bytes[1];
			result +=(char)bytes[2];
		}
		else if (s.charAt(length-2)=='='){
			String temp = s.substring((length/4-1)*4,(length/4)*4);
			int[] bytes = transform(temp);
			result +=(char)bytes[0];
		}
		else {
			String temp = s.substring((length/4-1)*4,(length/4)*4);
			int[] bytes = transform(temp);
			result +=(char)bytes[0];
			result +=(char)bytes[1];
		}
		return result;
	}
}
