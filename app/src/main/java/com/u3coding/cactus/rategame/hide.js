function getClass(parent,sClass)
{
	var aEle=parent.getElementsByTagName('div');
	var aResult=[];
	var i=0;
	for(i<0;i<aEle.length;i++)
	{
		if(aEle[i].className==sClass)
		{
			aResult.push(aEle[i]);
		}
	};
	return aResult;
}

function hideOther()
{
	getClass(document,'global header')[0].style.display='none';

}