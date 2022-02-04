package alg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Subtitle {
	
	ArrayList<Word> list;
        String filePath;
	
	public Subtitle(String filePath) throws FileNotFoundException {
		list = new ArrayList<Word>();
                this.filePath = filePath;
                getFile(filePath);
	}

	
	public void getFile(String filePath) throws FileNotFoundException {
                File f = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(f));
		//BufferedReader reader = new BufferedReader(new FileReader("twd.srt"));
		String line;
		double time = 0;
		LineType lineType = LineType.NUMBER;
		try {
			while ((line = reader.readLine()) != null)
			{
				if(lineType == LineType.TIME) {
					lineType = getLineType(line);
					if (lineType == LineType.TEXT) {
						ArrayList<String> words = getWords(line);
						for (int i = 0; i < words.size(); i++) {
							boolean haveWord = false;
							for (int j = 0; j < list.size(); j++) {
								if (list.get(j).word.equals(words.get(i).toLowerCase())) {
									list.get(j).time += time;
									list.get(j).quantity += 1;
									haveWord = true;
								}
							}
							if (!haveWord) {
								list.add(new Word(words.get(i).toLowerCase(), time, 1));
							}
						}
					}
				} else {
					lineType = getLineType(line);
					if (lineType == LineType.TIME) {
						time = getTime(line);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double getTime(String line) {
		// 00:30:00,131 --> 00:30:02,425
		// 01234567890123456789012345678
		double time = getSeconds(line.substring(17, 29)) - getSeconds(line.substring(0, 12));
		return time;
	}
	
	public double getSeconds(String time) {
		// 00:30:00,131
		// 012345678901
		double seconds = 3600.0 * Integer.parseInt(time.substring(0, 2))
				+ 60 * Integer.parseInt(time.substring(3, 5))
				+ Integer.parseInt(time.substring(6, 8))
				+ Integer.parseInt(time.substring(9, 12)) / 1000.0;
		return seconds;
	}
	
	public ArrayList<String> getWords(String line){
		int beginIndex = 0;
		boolean ok = true;
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i < line.length(); i++) {
			if(beginIndex == i && !Character.isLetter(line.charAt(i))) {
				beginIndex = i + 1;
			}
			if(ok && line.charAt(i) == '<') {
				ok = false;
				if(beginIndex < i) {
					if (line.substring(beginIndex, i).length() > 3)
					words.add(line.substring(beginIndex, i));
				}
			} else if(line.charAt(i) == '>' && ok == false) {
				ok = true;
				beginIndex = i + 1;
			} else if (ok && !Character.isLetter(line.charAt(i)) && beginIndex < i) {
				if (line.substring(beginIndex, i).length() > 3)
				words.add(line.substring(beginIndex, i));
				beginIndex = i + 1;
			} else if (i == line.length() - 1 && ok && beginIndex < i) {
				if (line.substring(beginIndex, i + 1).length() > 3)
					words.add(line.substring(beginIndex, i + 1));
			}
		}
		return words;
	}
	
	public LineType getLineType(String line) {
		if(line.length() == 0) {
			return LineType.EMPTY;
		} else if (line.length() == 29 && line.substring(13, 16).equals("-->")) {
			return LineType.TIME;
		} else {
			return LineType.TEXT;
		}
	}
	
	public String getLongestTimeWord() {
		double max = 0;
		String word = "";
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).time > max) {
				max = list.get(i).time;
				word = list.get(i).word;
			}
		}
		return word;
	}
	
	public int getWordQuantity(String word) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).word.equals(word)) {
				return list.get(i).quantity;
			}
		}
		return 0;
	}
	
	public double getWordTime(String word) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).word.equals(word)) {
				return list.get(i).time;
			}
		}
		return 0;
	}
	
	public String shiftSubtitle(double shiftTime) throws FileNotFoundException {
		File f = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(f));
		String line;
                boolean ok = true;
		LineType lineType = LineType.NUMBER;
		try {
			FileWriter myWriter = new FileWriter(filePath.substring(0, filePath.length() - 4) + "_" + shiftTime + ".srt");
			while ((line = reader.readLine()) != null)
			{
				lineType = getLineType(line);
				if(lineType == LineType.TIME) {
                                        if (shiftTimeLine(line, shiftTime) == null){
                                            ok = false;
                                        }
					myWriter.write(shiftTimeLine(line, shiftTime) + "\n");
				} else {
					myWriter.write(line + "\n");
				}
			}
			myWriter.close();
                        if(!ok){
                            File myObj = new File(filePath.substring(0, filePath.length() - 4) + "_" + shiftTime + ".srt"); 
                            if (myObj.delete()) { 
                              System.out.println("Deleted the file: " + myObj.getName());
                            }
                            return "minusError";
                        } else {
                            return "ok";
                        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
                        return "ioError";
		}
	}
	
	public String shiftTimeLine(String line, double shiftTime) {
		// 00:30:00,131 --> 00:30:02,425
		// 01234567890123456789012345678
		String first = getTimeString(getSeconds(line.substring(0, 12)) + shiftTime);
                String second = getTimeString(getSeconds(line.substring(17, 29)) + shiftTime);
                if (first != null && second != null){
                    return first + " --> " + second;
                } else {
                    return null;
                }
		
	}
	
	public String getTimeString(double seconds) {
		int hour = 0;
		int minutes = 0;
		double sec = 0;
		
		hour = (int) (seconds / 3600);
		minutes = (int) ((seconds - hour * 3600) / 60);
		sec = seconds - hour * 3600 -  minutes * 60;
	
		DecimalFormat formatter = new DecimalFormat("00");
		DecimalFormat formatter2 = new DecimalFormat("00.000");
                if(hour >= 0 && minutes >= 0 && sec >= 0){
                    return formatter.format(hour) + ":" + formatter.format(minutes) + ":" + formatter2.format(sec);
                } else {
                    return null;
                }
	}
}
