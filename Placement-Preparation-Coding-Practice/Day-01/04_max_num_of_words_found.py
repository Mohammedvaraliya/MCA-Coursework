class Solution:
    def mostWordsFound_brute_force(self, sentences: list[str]) -> int:
        count = 0
        res = 0
        for sentence in sentences:
            for word in range(len(sentence)):
                if sentence[word] == " ":
                    count += 1
            count += 1
            res = max(res, count)
            count = 0
        
        return res

    def mostWordsFound(self, sentences: list[str]) -> int:
        res = 0
        for sentence in sentences:
            res = max(res, len(sentence.split()))
        return res


if __name__ == "__main__":
        
        obj = Solution()

        sentences1 = ["alice and bob love leetcode", "i think so too", "this is great thanks very much"]
        print(obj.mostWordsFound(sentences1))

        sentences2 = ["please wait", "continue to fight", "continue to win"]
        print(obj.mostWordsFound(sentences2))