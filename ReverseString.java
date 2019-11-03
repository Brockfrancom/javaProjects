public class ReverseString {
    public static void main(String[] args) {
        System.out.println(reverseString("The 2008 Universal fire erupted June 1 on the back lot of Universal Studios Hollywood, an American film studio and theme park in the San Fernando Valley area of Los Angeles County, California, United States. The fire began when a worker used a blowtorch to warm asphalt shingles being applied to a facade.[1][2] They left before checking that all spots had cooled and a three-alarm fire broke out. Nine firefighters and a Los Angeles County sheriffs' deputy sustained minor injuries. The fire was put out after twelve hours. Universal Pictures claimed at the time that the fire only destroyed a three-acre (1.2 ha) portion of the Universal backlot (including the attraction King Kong Encounter)[3][4] and 40,000 to 50,000 archived digital video and film copies. A June 2019 New York Times Magazine exposé asserted that the fire further destroyed 118,000 to 175,000 audio master tapes belonging to Universal Music Group (UMG). This included original recordings belonging to some of the best-selling artists worldwide, such as Cher, Eric Clapton, Neil Diamond, the Eagles, Eminem, Guns N' Roses, Janet Jackson, Elton John, Olivia Newton-John, George Strait, Barry White and the Who. UMG initially disputed the story, but CEO Lucian Grainge later confirmed that there had been a significant loss of the musical archives."));
        System.out.println(reverseWords("The 2008 Universal fire erupted June 1 on the back lot of Universal Studios Hollywood, an American film studio and theme park in the San Fernando Valley area of Los Angeles County, California, United States. The fire began when a worker used a blowtorch to warm asphalt shingles being applied to a facade.[1][2] They left before checking that all spots had cooled and a three-alarm fire broke out. Nine firefighters and a Los Angeles County sheriffs' deputy sustained minor injuries. The fire was put out after twelve hours. Universal Pictures claimed at the time that the fire only destroyed a three-acre (1.2 ha) portion of the Universal backlot (including the attraction King Kong Encounter)[3][4] and 40,000 to 50,000 archived digital video and film copies. A June 2019 New York Times Magazine exposé asserted that the fire further destroyed 118,000 to 175,000 audio master tapes belonging to Universal Music Group (UMG). This included original recordings belonging to some of the best-selling artists worldwide, such as Cher, Eric Clapton, Neil Diamond, the Eagles, Eminem, Guns N' Roses, Janet Jackson, Elton John, Olivia Newton-John, George Strait, Barry White and the Who. UMG initially disputed the story, but CEO Lucian Grainge later confirmed that there had been a significant loss of the musical archives."));
    }
    public static String reverseString(String phrase){
        String reversePhrase = "";
        for(int i = phrase.length()-1;i>=0;i--) {
            reversePhrase = reversePhrase + phrase.charAt(i);
        }
        return reversePhrase;
    }
    public static String reverseWords(String phrase){
        String[] words = phrase.split(" ");
        String reversePhrase = "";
        for(int i = words.length-1;i>=0;i--) {
            reversePhrase = reversePhrase + words[i];
            reversePhrase = reversePhrase + " ";
        }
        return reversePhrase;
    }
}
