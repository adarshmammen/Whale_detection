clear all
srcFiles = dir('C:\Users\BOB0021\Documents\MATLAB\pos\*.jpg');  % the folder in which ur images exists
Xdata=[];
Ydata=[];
for i = 1 : size(srcFiles,1)
    filename=strcat('C:\Users\BOB0021\Documents\MATLAB\pos\',srcFiles(i).name);
    I = imread(filename);
    I = rgb2gray(I);
    if(size(I,1)==256 && size(I,2)==256)
    I = extractHOGFeatures(I,'CellSize',[8 8]);
    I = reshape(I,1,34596);
    Xdata=[Xdata;I];
    Ydata=[Ydata;1];
    end
end
fprintf('training data positive imported\n')

srcFiles2 = dir('C:\Users\BOB0021\Documents\MATLAB\neg\*.jpg');
for i=1:size(srcFiles2,1)
    filename2=strcat('C:\Users\BOB0021\Documents\MATLAB\neg\',srcFiles2(i).name);
    I = imread(filename2);
    disp(filename2)
    I = rgb2gray(I);
    if(size(I,1)==256 && size(I,2)==256)
    I = extractHOGFeatures(I,'CellSize',[8 8]);
    I = reshape(I,1,34596);
    Xdata = [Xdata;I];
    Ydata = [Ydata;0];
    end
end

fprintf('testing data negative imported\n')

cv=cvpartition(Ydata,'Kfold',4);
trIdx = cv.training(1);
teIdx = cv.test(1);

Xtrain=Xdata(trIdx,:);
Ytrain=Ydata(trIdx,:);
Xtest = Xdata(teIdx,:);
Ytest = Ydata(teIdx,:);
fprintf('start SVM training\n')
svmstruct=svmtrain(double(Xtrain),Ytrain,'kernel_function','linear');
fprintf('start testing\n')
ypredict=svmclassify(svmstruct,double(Xtest));
cm=confusionmat(Ytest,ypredict);
ccr=sum(sum(diag(cm),1),2)/sum(sum(cm,1),2);
fprintf('done\n')

% %% training single image
% filename_single='test_neg.jpg';
% I = imread(filename_single);
% I = rgb2gray(I);
% if(size(I,1)==256 && size(I,2)==256)
% I = extractHOGFeatures(I,'CellSize',[8 8]);
% I = reshape(I,1,34596);
% end
% ypredict_single=svmclassify(svmstruct,double(I));

