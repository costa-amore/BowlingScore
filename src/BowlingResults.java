
public class BowlingResults {
    public static int answer(String all_throws_result_list)  {
        all_throws_result_list = all_throws_result_list.replace("-","0");
        String[] frames = all_throws_result_list.split("\\|");

        if (frames[0].contains("/")) {
            if (calculateFrameResult(frames, 1) < 10) {
                return sumOfAllFrames(frames);
            }
            return 150;
        }
        return sumOfAllFrames(frames);
    }

    private static int calculateFrameResult(String[] frames, int frameNumber) {
        String frame = frames[frameNumber];

        String firstThrow = replaceStrikeWithTen(frame);
        String secondThrow = "0";

        if (notaStrike(firstThrow)) {
            secondThrow = getSecondThrow(frame);
            if (isASpare(secondThrow)){
                return 10 + firstThrowOfNextFrame(frames, frameNumber);
            }
        } else {
            if (frameNumber == 9) {
                return 10 + throwsInFrame(frames, frameNumber +2);
            }
            if (frameNumber > 9) {
                return 10;
            }
            return 10 + throwsInFrame(frames, frameNumber +1);
        }

        return sumOfTwoThrows(firstThrow, secondThrow);
    }

    private static int throwsInFrame(String[] frames, int frameNumber) {
        String frame = frames[frameNumber];

        String firstThrow = replaceStrikeWithTen(frame);
        String secondThrow = "0";

        if (notaStrike(firstThrow)) {
            secondThrow = getSecondThrow(frame);
            if (isASpare(secondThrow)){
                return 10;
            }
        } else {
            return 10;
        }

        return sumOfTwoThrows(firstThrow, secondThrow);
    }

    private static int firstThrowOfNextFrame(String[] frames, int frameNumber)  {
        return Integer.parseInt(getFirstThrow(frames[frameNumber+1]));
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

    private static String getFirstThrow(String frame) {
        return String.valueOf(frame.charAt(0));
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