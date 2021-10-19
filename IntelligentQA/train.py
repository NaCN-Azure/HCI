
import jieba
def read_file(filename):

    f=open(filename,'r',encoding='utf-8')
    line = f.readline()
    str=[]
    user_dict = [#todo
        ('华住会','n',1),('华住','n',10000), ('锦江','ng',10000),('锦江之星','nb',15000),('锦江之星风尚','nb',20000),
        ('nng','nng',10000),('nnb','nnb','10000'),('nnr','nnr',10000),('nnv','nnv',100000)]
    for item in user_dict:
        jieba.add_word(item[0], tag=item[1],freq=item[2])

    while line:
        fenci = jieba.lcut(line,cut_all=False)
        str.append(list(set(fenci)))
        line = f.readline()
    return str

from  nltk.probability import FreqDist,ConditionalFreqDist
from nltk.metrics import  BigramAssocMeasures

#jieba分词特征标记
def jieba_features(number):
    q0Words = []     #q0类型的words
    q2Words = []
    q1Words = []
    q3Words = []
    q4Words =[]
    q5Words = []
    q6Words = []
    q7Words = []
    q8Words = []
    q9Words = []
    q10Words=[]
    q11Words=[]

    Words=[q0Words,q2Words,q1Words,q3Words,q4Words,
           q5Words,q6Words,q7Words,q8Words,
           q9Words,q10Words,q11Words,]#todo
    filename=['q0','q2','q1','q3','q4',
              'q5','q6','q7','q8',
              'q9','q10','q11',]#todo
    for i in range(len(filename)):
        for items in read_file('question/'+filename[i]+'.txt'):
            for item in items:
                Words[i].append(item)   #添加对应样本


    word_fd=FreqDist()      #所有words的频率统计
    cond_word_fd = ConditionalFreqDist()    #条件words频率统计

    for word in q0Words:
        word_fd[word] += 1
        cond_word_fd['q0'][word]+=1
    for word in q1Words:
        word_fd[word]+=1
        cond_word_fd['q1'][word]+=1
    for word in q2Words:
        word_fd[word] += 1
        cond_word_fd['q2'][word] += 1
    for word in q3Words:
        word_fd[word]+=1
        cond_word_fd['q3'][word]+=1
    for word in q4Words:
        word_fd[word]+=1
        cond_word_fd['q4'][word]+=1
    for word in q5Words:
        word_fd[word]+=1
        cond_word_fd['q5'][word]+=1
    for word in q6Words:
        word_fd[word]+=1
        cond_word_fd['q6'][word]+=1
    for word in q7Words:
        word_fd[word]+=1
        cond_word_fd['q7'][word]+=1
    for word in q8Words:
        word_fd[word]+=1
        cond_word_fd['q8'][word]+=1
    for word in q9Words:
        word_fd[word]+=1
        cond_word_fd['q9'][word]+=1
    for word in q10Words:
        word_fd[word]+=1
        cond_word_fd['q10'][word]+=1
    for word in q11Words:
        word_fd[word]+=1
        cond_word_fd['q11'][word]+=1
   #todo

    q0_word_count = cond_word_fd['q0'].N()    #q1 words数量
    q1_word_count = cond_word_fd['q1'].N()
    q2_word_count = cond_word_fd['q2'].N()
    q3_word_count = cond_word_fd['q3'].N()
    q4_word_count = cond_word_fd['q4'].N()

    q5_word_count = cond_word_fd['q5'].N()
    q6_word_count = cond_word_fd['q6'].N()
    q7_word_count=cond_word_fd['q7'].N()
    q8_word_count=cond_word_fd['q8'].N()

    q9_word_count=cond_word_fd['q9'].N()
    q10_word_count=cond_word_fd['q10'].N()
    q11_word_count=cond_word_fd['q11'].N()
  #todo

    #总词数
    total_word_count = (q0_word_count + q1_word_count + q2_word_count + q3_word_count + q4_word_count
                        + q5_word_count + q6_word_count + q7_word_count + q8_word_count
                        + q9_word_count + q10_word_count + q11_word_count)#todo

    word_scores ={}

    for word,fred in word_fd.items():
        q0_score = BigramAssocMeasures.chi_sq(cond_word_fd['q0'][word],(fred,q0_word_count),
                                                 total_word_count)      #q0words的卡方统计量
        q1_score = BigramAssocMeasures.chi_sq(cond_word_fd['q1'][word],(fred,q1_word_count),
                                                total_word_count)
        q2_score = BigramAssocMeasures.chi_sq(cond_word_fd['q2'][word], (fred, q2_word_count),
                                              total_word_count)
        q3_score=BigramAssocMeasures.chi_sq(cond_word_fd['q3'][word],(fred,q3_word_count),
                                                 total_word_count)
        q4_score = BigramAssocMeasures.chi_sq(cond_word_fd['q4'][word], (fred, q4_word_count),
                                                   total_word_count)
        q5_score = BigramAssocMeasures.chi_sq(cond_word_fd['q5'][word], (fred, q5_word_count),
                                                   total_word_count)
        q6_score = BigramAssocMeasures.chi_sq(cond_word_fd['q6'][word], (fred, q6_word_count),
                                                   total_word_count)
        q7_score = BigramAssocMeasures.chi_sq(cond_word_fd['q7'][word], (fred, q7_word_count),
                                                    total_word_count)
        q8_score = BigramAssocMeasures.chi_sq(cond_word_fd['q8'][word], (fred, q8_word_count),
                                                   total_word_count)
        q9_score = BigramAssocMeasures.chi_sq(cond_word_fd['q9'][word], (fred, q9_word_count),
                                                   total_word_count)
        q10_score = BigramAssocMeasures.chi_sq(cond_word_fd['q10'][word], (fred, q10_word_count),
                                                   total_word_count)
        q11_score = BigramAssocMeasures.chi_sq(cond_word_fd['q11'][word], (fred, q11_word_count),
                                                   total_word_count)
      #todo
        word_scores[word]=(q0_score+q1_score+q2_score+q3_score+q4_score+
                            q5_score+q6_score+q7_score+q8_score+
                           q9_score+q10_score+q11_score)#todo
        #一个词的信息量等于所有类型词的卡方统计量之和

    best_vals = sorted(word_scores.items(),key=lambda item:item[1],reverse=True)[:number]
    #信息量倒序排序
    best_words=set([w for w,s in best_vals])
    return dict([(word,True) for word in best_words])   #格式化返回
#print(jieba_features(100))

def build_features():
    #feature = bag_of_words(text())
    #feature = bigram(text(),score_fn=BigramAssocMeasures.chi_sq,n=300)
    #feature=bigram_words(text(),score_fn=BigramAssocMeasures.chi_sq,n=500)
    feature=jieba_features(1000)    #选择jieba特征

    q0Features = []
    q1Features=[]
    q2Features=[]
    q3Features=[]
    q4Features=[]
    q5Features=[]
    q6Features=[]
    q7Features=[]
    q8Features=[]
    q9Features=[]
    q10Features=[]
    q11Features=[]
    #todo

    Features=[q0Features,q2Features,q1Features,q3Features,q4Features,
              q5Features,q6Features,q7Features,q8Features,
              q9Features,q10Features,q11Features,]#todo
    filename = ['q0', 'q1','q2', 'q3', 'q4',
                'q5', 'q6', 'q7', 'q8',
                'q9', 'q10', 'q11', ]#todo
    for i in range(len(filename)):
        for items in read_file('question/' + filename[i] + '.txt'):
            a={}
            for item in items:
                if item in feature.keys():
                    a[item]='True'
            fWords=[a,filename[i]]
            Features[i].append(fWords)      #给各种情感词语标记标签

    return Features

Features=build_features()

train=[]
test=[]
from random import shuffle
import math
for i in range(12):#todo
    shuffle(Features[i])    #shuffle避免顺序影响机器学习
    point=math.ceil(len(Features[i])*0.2)   #二八定律分割样本
    train=train+Features[i]
    test=test+Features[i]


testdata,tag = zip(*test)   #test解析矩阵
#print(testdata)

from nltk.classify.scikitlearn import SklearnClassifier
import sklearn
import pickle
def score(classifier):
    classifier = SklearnClassifier(classifier)
    classifier.train(train)

    pred = classifier.classify_many(testdata)   #对测试集进行标记
    n=0
    s=len(pred)
    for i in range(0,s):
        if pred[i] == tag[i]:   #对比解析的人工标注tag
            n=n+1       #相同加一
        else:
            print("!!!")
            print(testdata[i])
    #return n/s
    with open('trained_model.pickle','wb') as f:
        pickle.dump(classifier,f)
    return n/s,classifier   #返回精确值和训练后的模型

from  sklearn.svm import  SVC,LinearSVC,NuSVC
from sklearn.naive_bayes import MultinomialNB,BernoulliNB
from sklearn.linear_model import  LogisticRegression
from sklearn.metrics import  accuracy_score


print('LinearSVC\'s accuracy is %f' % score(LinearSVC())[0])
import jieba.posseg as pseg
def predict():
    stopwords1 = [line.rstrip() for line in
                  open('stopwords/stopwords-master/baidu_stopwords.txt', 'r', encoding='utf-8')]
    stopwords2 = [line.rstrip() for line in
                  open('stopwords/stopwords-master/cn_stopwords.txt', 'r', encoding='utf-8')]
    stopwords3 = [line.rstrip() for line in
                  open('stopwords/stopwords-master/hit_stopwords.txt', 'r', encoding='utf-8')]
    stopwords4 = [line.rstrip() for line in
                  open('stopwords/stopwords-master/scu_stopwords.txt', 'r', encoding='utf-8')]
    stopwords = stopwords1 + stopwords2 + stopwords3 + stopwords4

    ques=input("enter ur question: ")
    page=[]
    classifier=score(LinearSVC())[1]

    words_list = pseg.lcut(ques)
    meaninful_words = {}
    process_result={}
    for word, flag in words_list:
        if word not in stopwords:
            meaninful_words[word] = 'True'
            process_result[word]= flag
    print(meaninful_words)
    print(process_result)

    pred = classifier.classify(meaninful_words)
    print(pred)


predict()

