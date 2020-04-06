import java.util.ArrayList;
import java.util.Arrays;

class Main{
    public static void main(String[] args){
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        int[] answer = solution(words, queries);
    }

    public static int[] solution(String[] words, String[] queries){
        int[] answer = new int[queries.length];
        ArrayList<String> queryList = new ArrayList<>(Arrays.asList(queries));
        queries = queryList.toArray(new String[queryList.size()]);
        
        Arrays.sort(words);
        
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int query_len = query.length();
            int num =0;

            for (int j = 0; j < words.length; j++) {
                String word = words[j];
                int word_len = word.length();

                if(word_len != query_len)
                    continue;
                else{
                    int idx = query.indexOf("?");
                    if(idx != 0){
                        String sub = query.substring(0, idx);
                        if(sub.equals(word.substring(0, idx)))
                            num++;
                    }
                    else{
                        int lastIdx = query.lastIndexOf("?");
                        String sub = query.substring(lastIdx+1, query.length());
                        if(sub.equals(word.substring(lastIdx +1, word.length())))
                            num++;
                    }
                }

            }
            answer[i] = num;
        }




        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
        return answer;
    }
}