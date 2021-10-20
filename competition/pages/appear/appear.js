// pages/appear/appear.js
var util = require('../../utils/util.js')
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: ['国家级', '省级', '市级','校级'],
    switch1Checked:true,
    multiArray_pinpai: [ '一等奖','二等奖','三等奖','其他'], //项目类型选择  
    multiIndex_pinpai: 0,//默认是0，下标最大9
    index: 0,
    fileIds: [],
    level:4,
    type:4,
    dateTime: util.formatTime(new Date()),
    projectList:{},
    imgnember: 0,
    projectIndex:0,
    uploadimgs: [], //上传图片列表
    editable: false, //是否可编辑
    imageUrl: "",
    selectShow: false,//控制下拉列表的显示隐藏，false隐藏、true显示
    index: 0,//选择的下拉列表下标
    projectId:"",
    content:""
  
  }, 
  bindShowMsg() {
    this.setData({
      select: !this.data.select
    })
},
bindPickerChangeProject:function(e){
  let that=this;
  console.log(e.detail.value)
  that.setData({
   projectIndex: e.detail.value
  })
},
 //获奖级别选择
 bindPickerChange: function(e) {
   let that=this;
  console.log('picker发送选择改变，携带值为', 4-e.detail.value)
  that.setData({
   type: 4-e.detail.value
  })
 },
  //获奖名次选择
  bindPickerChangess: function (e) {
    let that=this;
    console.log('picker发送选择改变，携带值为', e.detail.value)
    that.setData({
      multiIndex_pinpai:e.detail.value,
      level: 4-e.detail.value,
    })
  },
//  提交
 formSubmit(e) {
   let that=this;
   e.detail.value.type=that.data.type;
   e.detail.value.level=that.data.level;
   if(e.detail.value.projectId==null){
    wx.showToast({
      title: '请选择比赛！',
      icon: 'error',
      duration: 2000
    })
   }else if(that.data.uploadimgs[0]==""||that.data.uploadimgs[0]==null){
    wx.showToast({
      title: '未上传图片！',
      icon: 'error',
      duration: 2000
    })
   }
   if(e.detail.value.content!=null){
     that.setData({
      content:e.detail.value.content
     })

   }else{
    that.setData({
      content:"无其他"
     })
   }
   wx.uploadFile({
    url: app.globalData.apiUrl+'/wx/applyAwards', 
    filePath: that.data.uploadimgs[0],
    name: 'image',
    formData: {
      projectId:e.detail.value.projectId,
      type:that.data.type,
      level:that.data.level,
      imageUrl:that.data.imageUrl,
      content:that.data.content
    },
    success (res){
      var response=JSON.parse(res.data);
      console.log(response);
      if(response.code==200){
        wx.showToast({
          title: '上报成功！',
          icon: 'success',
          duration: 4000,
        })
        wx.redirectTo({
          url: '../myself/myself',
        })
      }else{
        wx.showToast({
          title: '系统异常',
          icon: 'error',
          duration: 4000,
        })
      }
    }
  })
},

  chooseImage: function () {
      let _this = this;
      wx.showActionSheet({
          itemList: ['从相册中选择', '拍照'],
          itemColor: "#f7982a",
          success: function (res) {
              if (!res.cancel) {
                  if (res.tapIndex == 0) {
                      _this.chooseWxImage('album')
                  } else if (res.tapIndex == 1) {
                      _this.chooseWxImage('camera')
                  }
              }
          }
      })
  },
  chooseWxImage: function (type) {
      let _this = this;
      var chooseNamber = 4 - this.data.imgnember;
      if (chooseNamber == 0) {

      } else {
          wx.chooseImage({
              count: chooseNamber,
              sizeType: ['original', 'compressed'],
              sourceType: [type],
              success: function (res) {
                  _this.setData({
                      uploadimgs: _this.data.uploadimgs.concat(res.tempFilePaths),
                      imgnember: _this.data.imgnember + res.tempFilePaths.length
                  });
                  console.log(_this.data.uploadimgs);
              }
          })
      }

  },
  editImage: function () {
      this.setData({
          editable: !this.data.editable
      })
  },
  ViewImage(e) {
      wx.previewImage({
          urls: this.data.uploadimgs,
          current: e.currentTarget.dataset.url
      });
  },
  deleteImg: function (e) {
      const imgs = [];
      console.log(this.data.uploadimgs)
      for (var i = 0; i < this.data.uploadimgs.length; i++) {
          if (i == e.currentTarget.dataset.index) {
              continue;
          } else {
              imgs.push(this.data.uploadimgs[i]);
          }
      }
      this.setData({
          uploadimgs: imgs,
          imgnember: imgs.length
      })
  },
  bindPickerChange: function (e) {
      this.setData({
          index: e.detail.value
      })
      console.log(this.data.index);
  },
  transformationBase64() {
      var fsm = wx.getFileSystemManager();
      var _this = this;
      for (var i = 0; i < this.data.uploadimgs.length; i++) {
          fsm.readFile({
              filePath: _this.data.uploadimgs[i],
              encoding: "base64",
              success(res) {
                  var base64 = "" + res.data
                  base64=base64.replace('+',"%2B");
                  _this.setData({
                    imageUrl:base64
                  });
              }

          })
      }
  },

  onClose(e) {
    var idx = e.currentTarget.dataset.idx
    var fileTem = this.data.fileIds;
    fileTem.splice(idx, 1)
    this.setData({
      fileIds: fileTem
    })
  },

 
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    let that=this;
    wx.request({
      url: app.globalData.apiUrl+'/wx/getStudentProjects',
      method: "GET", //提交方式
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data:{
        studentId:wx.getStorageSync('studentId'),
      },
      success:function(res){
        that.setData({
          projectList:res.data
        })
      }
    });
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})