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
         				header : 'Tweet Details',
         				dataIndex : 'tweetDesc',
         				sortable:true,
         				width    :400,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;

         				}
         			},
         			{
         				header : 'User ID',
                		dataIndex : 'userId',
                		sortable:true,
                		width:150
         			},
         			{
         				header : 'Screen Name',
                		dataIndex : 'screenName',
                		sortable:true,
                		width:150
         			},
         			{
         				header : 'Language',
                		dataIndex : 'language',
                		sortable:true,
                		width:150
         			},
         			{
         				header : 'Hash Tag',
                		dataIndex : 'hashTag',
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
		           {name:'userId',mapping:'userId',type:'string'},
		           {name:'tweetDesc',mapping:'tweetDesc',type:'string'},
		           {name:'tweetId',mapping:'tweetId',type:'int'},
		           {name:'screenName',mapping:'screenName',type:'string'},
		           {name:'language',mapping:'language',type:'string'},
		           {name:'hashTag',mapping:'hashTag',type:'string'}
		          ]
		
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/tweet/viewTweets.do',
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
		title:'Tweets Information',
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
	
	
	
