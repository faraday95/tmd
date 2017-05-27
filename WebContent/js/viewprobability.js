Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});
Ext.tip.QuickTipManager.init();



var keycolumns=[
                	{
                		header : 'Tweet ID',
                		dataIndex : 'tweetId',
                		sortable:true,
                		width:50
                	},
         			{
         				header : 'Category Name',
         				dataIndex : 'catName',
         				sortable:true,
         				width    :400
         			},
         			{
         				header : 'Count',
                		dataIndex : 'count',
                		sortable:true,
                		width:150
         			},
         			{
         				header : 'Total Words',
                		dataIndex : 'totalWords',
                		sortable:true,
                		width:150
         			},
         			{
         				header : 'Probability',
                		dataIndex : 'probability',
                		sortable:true,
                		width:150
         			},
         			{
         				header : 'Negative Probability',
                		dataIndex : 'negativeProbability',
                		sortable:true,
                		width:150
         			}
         			];

var hideConfirmationMsg;
var showConfirmationMsg;
/* Hide the Confirmation Message */
	hideConfirmationMsg = function() {
		var confMsgDiv = Ext.get('confirmationMessage');
		confMsgDiv.dom.innerHTML = "";
		confMsgDiv.dom.style.display = 'none';
	}
	/* Show Confirmation Message */
	showConfirmationMsg = function(msg) {
		var confMsgDiv = Ext.get('confirmationMessage');
		confMsgDiv.dom.innerHTML =  msg;
		confMsgDiv.dom.style.display = 'inline-block';		
	}
	var keyStore;
Ext.onReady(function () {

	var loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading"});
	loadMask.show();
	
	Ext.define('keywordModel',{
		extend : 'Ext.data.Model',
		fields : [ 
		           {name:'tweetId',mapping:'tweetId',type:'int'},
		           {name:'catName',mapping:'catName',type:'string'},
		           {name:'count',mapping:'count',type:'int'},
		           {name:'totalWords',mapping:'totalWords',type:'int'},
		           {name:'probability',mapping:'probability',type:'double'},
		           {name:'negativeProbability',mapping:'negativeProbability',type:'double'}
		        ]
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/tweet/viewProbability.do',
			extraParams:{
				type:'1'
			},
			actionMethods:{
				read:'POST'
			},
			reader : {
				type :'json',
				root:'model'
			}
		},
		listeners:
		{
			'load':function(store, records){
						
				loadMask.hide();
			}
		},
		autoLoad : true
	});
	
	
	
	
	
	var keyGrid = Ext.create('Ext.grid.Panel', {
		title:'Clean Tweets Information',
		forceFit : true,
		id : 'keyGrid',
		store : keyStore,
		columns : keycolumns,
		width:1200,
		height:300,
		autoFit : true,
		autoscroll:true,
		stripRows:true,
		renderTo : 'keyContainer',
		collapsible:true,
		overflowY:'auto'
	});

});
	
	
	
