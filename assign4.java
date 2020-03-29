
public class assign4 {

    public static void main(String[] args) {
        String[] sImageIn =
        {
           " a                                             ",
           "                                               ",
           "                                               ",
           "     * * * * * * * * * * * * * * * * * * * * * ",
           "     *                                       * ",
           "     ****** **** ****** ******* ** *** *****   ",
           "     *     *    ****************************** ",
           "     * **    * *        **  *    * * *   *     ",
           "     *   *    *  *****    *   * *   *  **  *** ",
           "     *  **     * *** **   **  *    **  ***  *  ",
           "     ***  * **   **  *   ****    *  *  ** * ** ",
           "     *****  ***  *  * *   ** ** **  *   * *    ",
           "     ***************************************** ",  
           "                                               ",
           "                                            b  ",
           "                                               "
  
        };
        System.out.println("Start Program");
        BarcodeImage obj1 = new BarcodeImage(sImageIn);
 
    }
 
 }

class BarcodeImage {
    public static final int MAX_HEIGHT = 30;
    public static final int MAX_WIDTH = 65;

    private boolean[][] imageData;

    BarcodeImage(){
        imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];

        for(int row = 0; row < MAX_HEIGHT; row ++){
            for(int col = 0; col < MAX_WIDTH; col++){
                this.imageData[row][col] = false;
            }
        } 
    }
    BarcodeImage(String[] strData){

        this.imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
        int beginOfArrayRow = 0; 
        int beginOfArrayCol  = 0;
        int endOfArrayRow = 0;
        int endOfArrayCol = 0;

        boolean checkSize = true;

        if(checkSize == true){
        //Left Corner points   
       // if(checkSize(strData) == true){
            for(int strCol = 0; strCol < strData.length - 1; strCol++){
                System.out.println("strData[strCol] = [" + strCol + "] " + strData[strCol]);
                for(int strRow = 0; strRow < strData[strCol].length() - 1; strRow++){
                    System.out.println("strData[strRow].charAt(strRow) = [][" + strRow + "] " + strData[strCol].charAt(strRow));

                // for(int strRow = strData[strCol].length(); strRow >= 0; strRow --){
                //     System.out.println("Col length = " + strData.length);
                //     System.out.println("Row length " + strData[strCol].length());
                    // System.out.println("col "  + strCol);
                    // System.out.println("row = "  + strRow);

                  //  System.out.println("array[" + strRow + "][" + strCol + "] = "  + strData[strCol].charAt(strCol));
                    //  if(strData[strCol].charAt(strCol) == '*'){
                    //     System.out.println("strData[strCol].charAt(strCol) "  + strData[strCol].charAt(strCol));
                    //  }
                    //  if(strData[strCol].charAt(strCol) == 'c'){
                    //     System.out.println("!! C strData[strCol].charAt(strCol) "  + strData[strCol].charAt(strCol));
                    //  }
                    //  if(strData[strCol].charAt(strCol) == 't'){
                    //     System.out.println("!! t strData[strCol].charAt(strCol) "  + strData[strCol].charAt(strCol));
                    //  }
                }
            }
        }
    
    }
}
/*  
int barStopRow = 0, barStopCol = 0, barStartRow = 0, barStartCol = 0;
boolean breakflag = false;
  
if(checkSize(str_data))
{
//Find str_data barcode end point
    for(int i = str_data.length -1; i >= 0; i--)
    {
        for (int j = str_data[0].length() - 1; j >=0; j--)
        
        if(str_data[i].charAt(j) == '*')
        {
            barStopRow = i;
            barStopCol = j;
            breakflag = true;
            break;
        }
        if(breakflag == true)
        {
            break;
        }
    }
  
    breakflag = false;
    //find str_data barcode start point
    for(int i = 0 ; i < str_data.length; i++)
    {
        for (int j = 0; j < str_data[0].length() ; j++)
        
            if(str_data[i].charAt(j) == '*')
            {
                barStartRow = i;
                barStartCol = j;
                breakflag = true;
                break;
            }
            if(breakflag == true)
            {
                break;
            }
    }

    int colIndex;
    for(int m = barStartRow; m <= barStopRow; m++)
    {
        colIndex = 0;
        for(int n = barStartCol; n <= barStopCol; n++)
        {
            this.setPixel(MAX_HEIGHT -(barStopRow - m) - 1, colIndex++,
            (str_data[m].charAt(n) == '*') ? true : false);
        }
    }
}
}*/
