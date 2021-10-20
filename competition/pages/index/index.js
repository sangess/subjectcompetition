// pages/mycompetition/mycompetition.js
var app=getApp()
Page({

  data: {
    inputShowed: false,
    inputVal: "",
    list: [],
    lbtImage:[
      {imageUrl:app.globalData.apiUrl+"/assets/index/lbt01.png"},
      {imageUrl:app.globalData.apiUrl+"/assets/index/lbt02.jpg"},
      {imageUrl:app.globalData.apiUrl+"/assets/index/lbt03.jpg"}
    ],
    hasBind:false
  },
  showInput: function() {
    this.setData({
      inputShowed: true
    });
  },
  //取消
  hideInput: function() {
    this.setData({
      inputVal: "",
      inputShowed: false
    });
  },
  //清空输入框内容
  clearInput: function() {
    this.setData({
      inputVal: ""
    });
  },
  //获取输入框信息
  inputTyping: function(e) {
    this.setData({
      inputVal: e.detail.value
    });
    
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let that=this;
    wx.request({
      url: app.globalData.apiUrl+'/wx/allComps',
      method: 'GET',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success:function(res){
        that.setData({
          list:res.data,
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    let that=this;
    if (wx.getStorageSync("hasBind")) {
      /*console.log(wx.getStorage("hasBind"));*/
     that.setData({
       hasBind: wx.getStorageSync("hasBind"),
     }) 
    }
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})