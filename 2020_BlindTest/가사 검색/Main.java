class Main{
    public static void main(String[] args){
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        boolean answer = solution(words, queries);
    }

    public static boolean solution(String[] words, String[] queries){
        int[] answer = {};

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int query_len = query.length();
            int num =0;

            for (int j = 0; j < words.length; j++) {
                String word = words[j]
                int word_len = word.length();

                if(word_len != query_len)
                    continue;
                else{
                    int idx = word.indexOf("?");
                    
                }

            }
        }




        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
        return answer;
    }
}