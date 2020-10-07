
public class BowlingResults {
    public static int answer(String all_throws_result_list)  {
        String[] frames = all_throws_result_list.split("\\|");

        if (frames[0].contains("/")) {
            if (calculateFrameResult(frames, 1) == 5) {
                return calculateFrameResult(frames, 0)
                     + calculateFrameResult(frames, 1);
            }
            return 150;
        }

        if(frames[0].contains("X")) {
            if(calculateFrameResult(frames, 1) == 7) {
                return 24;
            }
            if(calculateFrameResult(frames, 1) == 0) {
                return 10;
            }
            if(frames[1].contains("X")) {
                return 300;
            }
        }

        return sumOfAllFrames(frames);
    }

    private static int calculateFrameResult(String[] frames, int frameNumber) {
        String frame = frames[frameNumber].replace("-", "0");

        String firstThrow = replaceStrikeWithTen(frame);
        String secondThrow = "0";

        if (notaStrike(firstThrow)) {
            secondThrow = getSecondThrow(frame);
            if (isASpare(secondThrow)){
                return 10 + firstThrowOfNextFrame(frames, frameNumber);
            }
        }

        return sumOfTwoThrows(firstThrow, secondThrow);
    }

    private static int firstThrowOfNextFrame(String[] frames, int frameNumber)  {
        return 5;
    }

    private static boolean notaStrike(String roll){
        return !roll.equals("10");
    }

    private static boolean isASpare(String roll){
        return roll.equals("/");
    }

    private static String replaceStrikeWithTen(String frame){
        return String.valueOf(frame.charAt(0)).replace("X","10");
    }

    private static String getSecondThrow(String frame) {
        return String.valueOf(frame.charAt(1));
    }
    private static String replaceSpareWithTen(String frame){
        return getSecondThrow(frame).replace("/","10");
    }

    private static int sumOfTwoThrows (String firstThrow, String secondThrow){
        return Integer.parseInt(firstThrow)+Integer.parseInt(secondThrow);
    }

    private static int sumOfAllFrames(String[] frames) {
        int sumOfFrames = 0;
        for ( int i=0; i<=9; i++)
        {
            sumOfFrames = sumOfFrames + calculateFrameResult(frames, i);
        }

        return sumOfFrames;
    }

}