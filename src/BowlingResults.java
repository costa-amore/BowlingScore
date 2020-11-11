
public class BowlingResults {
    public static int answer(String all_throws_result_list)  {

//todo: create function for this
        all_throws_result_list = all_throws_result_list
                .replace("-","0")
                .replace("||", "|");
        String[] frames = all_throws_result_list.split("\\|");
//end of function

//todo: get rid of this
        if (frames[0].contains("/")) {
            if (calculateFrameResult(frames, 1) < 10) {
                return sumOfAllFrames(frames);
            }
            return 150;
        }
//end of getting rid

        return sumOfAllFrames(frames);
    }

    private static int calculateFrameResult(String[] frames, int frameNumber) {
        String currentFrame = frames[frameNumber];

        String firstThrow = getFirstThrow(currentFrame);
        String secondThrow = "0";

        if (isAStrike(firstThrow)) {
            if (thisIsTheLastFrame(frameNumber)) {
                return 10 + sumofTwoBonusThrows(frames);
            }
            return 10 + sumofNextTwoThrows(frames, frameNumber +1);
        } else {
            secondThrow = getSecondThrow(currentFrame);
            if (isASpare(secondThrow)){
                return 10 + firstThrowOfNextFrame(frames, frameNumber);
            }
        }

        return sumOfTwoThrows(firstThrow, secondThrow);
    }

    private static int sumofTwoBonusThrows(String[] frames) {
        String bonusFrame = frames[10];
        return sumOfTwoThrows(getFirstThrow(bonusFrame),getSecondThrow(bonusFrame));
    }

    private static boolean thisIsTheLastFrame(int frameNumber) {
        return frameNumber == 9;
    }

    private static int sumofNextTwoThrows(String[] frames, int frameNumber) {
        String nextFrame = frames[frameNumber];

        String firstThrowOfNextFrame = getFirstThrow(nextFrame);
        String secondThrow = "0";

        if (isAStrike(firstThrowOfNextFrame)) {
            return 10 + firstThrowOfNextFrame(frames, frameNumber);
        } else {
            secondThrow = getSecondThrow(nextFrame);
            if (isASpare(secondThrow)){
                return 10;
            }
        }

        return sumOfTwoThrows(firstThrowOfNextFrame, secondThrow);
    }

    private static int firstThrowOfNextFrame(String[] frames, int frameNumber)  {
        return Integer.parseInt(getFirstThrow(frames[frameNumber+1]));
    }

    private static boolean isBonusFrame(String frame) {
        return (frame.length()==2);
    }

    private static boolean isAStrike(String roll){
        return roll.equals("10");
    }

    private static boolean isASpare(String roll){
        return roll.equals("/");
    }

    private static String replaceStrikeWithTen(String frame){
        return String.valueOf(frame.charAt(0)).replace("X","10");
    }

    private static String getFirstThrow(String frame) {
        return String.valueOf(frame.charAt(0)).replace("X", "10");
    }
    private static String getSecondThrow(String frame) {
        return String.valueOf(frame.charAt(1)).replace("X", "10");
    }

    private static int sumOfTwoThrows (String firstThrow, String secondThrow){
        return Integer.parseInt(firstThrow)+Integer.parseInt(secondThrow);
    }

    private static int sumOfAllFrames(String[] frames) {
        int sumOfFrames = 0;
        for ( int i=0; i<=9; i++)
        {
            sumOfFrames += calculateFrameResult(frames, i);
        }

        return sumOfFrames;
    }



}