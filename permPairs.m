function P=permPairs(N)
%Code obtained from https://www.mathworks.com/matlabcentral/fileexchange/4206-erdos-renyi-random-graph

%Produces all pairs of pairs from 1 to N.
%It is ~30% to 50% faster that nchoosek(1:N,2).
%Created by Pablo 02/12/2003

ini_i=1;
ini_j=ini_i+1;
r0=1;
P=[];
for i=ini_i:N-1
    lj=N-ini_i;
    P(:,r0:lj+r0-1)=[ones(1,lj)*i;ini_j:N];
    r0=r0+lj;
    ini_i=ini_i+1;
    ini_j=ini_i+1;
end
P=P';