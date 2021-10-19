import pickle
import jieba.posseg as pseg
import jieba
#todo load 自定义词库
def predict(question):

    stopwords1 = [line.rstrip() for line in
                  open('stopwords/stopwords-master/baidu_stopwords.txt', 'r', encoding='utf-8')]
    stopwords2 = [line.rstrip() for line in
                  open('stopwords/stopwords-master/cn_stopwords.txt', 'r', encoding='utf-8')]
    stopwords3 = [line.rstrip() for line in
                  open('stopwords/stopwords-master/hit_stopwords.txt', 'r', encoding='utf-8')]
    stopwords4 = [line.rstrip() for line in
                  open('stopwords/stopwords-master/scu_stopwords.txt', 'r', encoding='utf-8')]
    stopwords = stopwords1 + stopwords2 + stopwords3 + stopwords4

    with open('trained_model.pickle','rb') as f:
        model=pickle.load(f)
        jieba.load_userdict(r'userdict.txt')
        words_list = pseg.lcut(question)
        meaninful_words = {}
        process_result = {'nnb':'','nng':'','nnr':'','nnv':'','question':''}
        for word, flag in words_list:
            if word not in stopwords:
                if flag in ['nnb','nng','nnr','nnv']:
                    meaninful_words[flag]='True'
                    process_result[flag] = word
                else:
                    meaninful_words[word] = 'True'



        print(meaninful_words)  #我是华住金会员，入住全季酒店有免费早餐嘛
        pred = model.classify(meaninful_words)
        print(pred)
        process_result['question']=pred
        print(process_result)
        return process_result   #返回格式化分词结果
