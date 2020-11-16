
public class BowlingResults {

    public static final int LAST_FRAME = 9;
    public static final int FIRST_FRAME = 0;
    public static final int BONUS_FRAME = 10;

    public static final String SPARE_VALUE = "/";
    public static final String STRIKE_VALUE = "10";

    public static final int FIRST_THROW = 0;
    public static final int SECOND_THROW = 1;
    public static final int MAX_NR_OF_PINS = 10;

    public static int answer(String all_throws_result_list)  {
        String[] frames = preProcessingAllResults(all_throws_result_list);
        return sumOfAllFrames(frames);
    }

    private static String[] preProcessingAllResults( String all_throws_result_list) {
        all_throws_result_list = all_throws_result_list
                .replace("-","0")
                .replace("||", "|");
        return all_throws_result_list.split("\\|");
    }

    private static int calculateFrameResult(String[] frames, int frameNumber) {
        String currentFrame = frames[frameNumber];

        String firstThrow = getFirstThrow(currentFrame);
        String secondThrow = "0";

        if (isAStrike(firstThrow)) {
            if (thisIsTheLastFrame(frameNumber)) {
                return MAX_NR_OF_PINS + sumOfTwoBonusThrows(frames);
            }
            return MAX_NR_OF_PINS + sumOfNextTwoThrows(frames, frameNumber +1);
        } else {
            secondThrow = getSecondThrow(currentFrame);
            if (isASpare(secondThrow)){
                return MAX_NR_OF_PINS + firstThrowOfNextFrame(frames, frameNumber);
            }
        }

        return sumOfTwoThrows(firstThrow, secondThrow);
    }

    private static int sumOfTwoBonusThrows(String[] frames) {
        String bonusFrame = frames[BONUS_FRAME];
        return sumOfTwoThrows(getFirstThrow(bonusFrame),getSecondThrow(bonusFrame));
    }

    private static boolean thisIsTheLastFrame(int frameNumber) {
        return frameNumber == LAST_FRAME;
    }

    private static int sumOfNextTwoThrows(String[] frames, int frameNumber) {
        String nextFrame = frames[frameNumber];

        String firstThrowOfNextFrame = getFirstThrow(nextFrame);
        String secondThrow = "0";

        if (isAStrike(firstThrowOfNextFrame)) {
            return MAX_NR_OF_PINS + firstThrowOfNextFrame(frames, frameNumber);
        } else {
            secondThrow = getSecondThrow(nextFrame);
            if (isASpare(secondThrow)){
                return MAX_NR_OF_PINS;
            }
        }

        return sumOfTwoThrows(firstThrowOfNextFrame, secondThrow);
    }

    private static int firstThrowOfNextFrame(String[] frames, int frameNumber)  {
        return Integer.parseInt(getFirstThrow(frames[frameNumber+1]));
    }

    private static boolean isAStrike(String roll){
        return roll.equals(STRIKE_VALUE);
    }

    private static boolean isASpare(String roll){
        return roll.equals(SPARE_VALUE);
    }

    private static String getFirstThrow(String frame) {
        return String.valueOf(frame.charAt(FIRST_THROW)).replace("X", "10");
    }
    private static String getSecondThrow(String frame) {
        return String.valueOf(frame.charAt(SECOND_THROW)).replace("X", "10");
    }

    private static int sumOfTwoThrows (String firstThrow, String secondThrow){
        return Integer.parseInt(firstThrow)+Integer.parseInt(secondThrow);
    }

    private static int sumOfAllFrames(String[] frames) {
        int sumOfFrames = 0;
        for (int i = FIRST_FRAME; i<= LAST_FRAME; i++)
        {
            sumOfFrames += calculateFrameResult(frames, i);
        }

        return sumOfFrames;
    }



}