package control.jsontool;
/**
 * This class is used to adjust the structure of output json file
 * @author Jiaxuan Wang
 */
public class Tool{
    private boolean isTab = true;
    /**
     * This method converts String to json format.
     * @param strJson the input String
     * @return String.
     */
    public String stringToJSON(String strJson) {
        int tabNum = 0;
        StringBuffer jsonFormat = new StringBuffer();
        int length = strJson.length();
        for (int i = 0; i < length; i++) {
            char c = strJson.charAt(i);
            if (c == '{') {
                tabNum++;
                jsonFormat.append(c + "\n");
                jsonFormat.append(getSpaceOrTab(tabNum));
            } else if (c == '}') {
                tabNum--;
                jsonFormat.append("\n");
                jsonFormat.append(getSpaceOrTab(tabNum));
                jsonFormat.append(c);
            }

            else {
                jsonFormat.append(c);
            }
        }
        return jsonFormat.toString();
    }
    /**
     * This method decides to get space or tab.
     * @param tabNum the tab number
     * @return String.
     */
    public String getSpaceOrTab(int tabNum) {
        StringBuffer sbTab = new StringBuffer();
        for (int i = 0; i < tabNum; i++) {
            if (isTab) {
                sbTab.append('\t');
            } else {
                sbTab.append("    ");
            }
        }
        return sbTab.toString();
    }
}

