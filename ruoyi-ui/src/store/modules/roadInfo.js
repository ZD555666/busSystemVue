
const state={
  localSelect:{xPoint: '', yPoint: ''},
  oneRoadInfo: [{busNo:''}],
  oneSchedule:[],
  specificSchedule:[],
  stationSelect:{xPoint:'',yPoint:'',stationName:'',stationId: ''},
  stationSort:[{xPoint: '', yPoint: '', stationName: '', stationId: ''}],
  returnSort:[{xPoint: '', yPoint: '', stationName: '', stationId: ''}],
  mapStationSort:[
    {
    geometry: {
      type: 'Point',
      coordinates: [118.20011400, 24.51462700],
    },
    properties: {
      // icon: '/local.png',
      text: '',
      stationId: ''
    }
  }
  ],
  mapRoadSort:[
    {
      geometry: {
        type: 'LineString',
        coordinates: [
          [118.20011400, 24.51462700],
          [118.12075700, 24.47598800]
        ],
      },
      properties: {
        color: 'red'
      }
    }
  ]
}

const mutations={
  //查看某路线站点
  viewRoadStation:(state, roadList)=>{
    //界面显示启程
    var stations = [];
    for (let i = 0; i < roadList.rsList.length; i++) {
      var station = {};
      station =
        {
          xPoint: roadList.rsList[i].xpoint,
          yPoint: roadList.rsList[i].ypoint,
          stationName: roadList.rsList[i].stationName,
          stationId: roadList.rsList[i].stationId
        };
      stations.push(station);
    }
    state.stationSort= stations;
    //界面显示返程
    var stations2 = [];
    for (let i = 0; i < roadList.rsReList.length; i++) {
      var station2 = {};
      station2=
        {
          xPoint: roadList.rsReList[i].xpoint,
          yPoint: roadList.rsReList[i].ypoint,
          stationName: roadList.rsReList[i].stationName,
          stationId: roadList.rsReList[i].stationId
        }
      stations2.push(station2);
    }
    state.returnSort = stations2;

  },
  //初始化配置的路线
  clearRoadStation(state){
    state.stationSort= [{xPoint: '', yPoint: '', stationName: '', stationId: ''}];
    state.returnSort= [{xPoint: '', yPoint: '', stationName: '', stationId: ''}];
    state.mapStationSort=[];
    state.mapRoadSort=[];
  },
  //返程地图站点
  addReturnMapStation: (state) => {
    state.mapStationSort = [];
    var stations = [];
    for (let i = 0; i < state.returnSort.length; i++) {
      var station = {};
      station =
        {
          geometry: {
            type: 'Point',
            coordinates: [state.returnSort[i].xPoint, state.returnSort[i].yPoint],
          },
          properties: {
            // icon: '/local.png',
            text: state.returnSort[i].stationName,
            stationId: state.returnSort[i].stationId
          }
        }
      ;
      // console.log("station", station);
      stations.push(station);
    }
    state.mapStationSort = stations;
    // console.log("vuex", state.mapStationSort);
  },
  //返程地图线路
  addReturnMapRoad: (state) => {
    var returnSort = state.returnSort;
    if (state.returnSort.length >= 2) {
      state.mapRoadSort = [];
      var roads = [];
      for (let i = 0; i < state.returnSort.length - 1; i++) {
        var road = {};
        road = {
          geometry: {
            type: 'LineString',
            coordinates: [
              [returnSort[i].xPoint, returnSort[i].yPoint],
              [returnSort[i + 1].xPoint, returnSort[i + 1].yPoint]
            ],
          },
          properties: {
            color: 'red'
          }
        };
        console.log("road", road);
        roads.push(road);
      }
      state.mapRoadSort = roads;
      console.log("vuex", state.mapRoadSort);
    }
  },
  //地图点集合
  addMapStationSort: (state) => {
    state.mapStationSort = [];
    var stations = [];
    for (let i = 0; i < state.stationSort.length; i++) {
      var station = {};
      station =
        {
          geometry: {
            type: 'Point',
            coordinates: [state.stationSort[i].xPoint, state.stationSort[i].yPoint],
          },
          properties: {
            // icon: '/local.png',
            text: state.stationSort[i].stationName,
            stationId: state.stationSort[i].stationId
          }
        }
      ;
      // console.log("station", station);
      stations.push(station);
    }
    state.mapStationSort = stations;
    // console.log("vuex", state.mapStationSort);

  },
  //地图路线集合
  addMapRoadSort:(state)=>{
    var stationSort= state.stationSort;
    if (state.stationSort.length>=2){
      state.mapRoadSort = [];
      var roads = [];
      for (let i = 0; i < state.stationSort.length - 1; i++) {
        var road = {};
        road = {
          geometry: {
            type: 'LineString',
            coordinates: [
              [stationSort[i].xPoint, stationSort[i].yPoint],
              [stationSort[i + 1].xPoint, stationSort[i + 1].yPoint]
            ],
          },
          properties: {
            color: 'red'
          }
        };
        // console.log("road", road);
        roads.push(road);
      }
      state.mapRoadSort = roads;
      // console.log("vuex", state.mapRoadSort);
    }
  },
  //添加反向路线某站点
  addReturn: (state, returnIndex) => {
    state.returnSort.splice(returnIndex + 1, 0, state.stationSelect);
  },
  //删除反向路线某站点
  deleteReturn:(state,returnIndex)=>{
    state.returnSort.splice(returnIndex, 1);
  },
  //生成反向路线
  createReturn:(state)=>{
    state.returnSort=[];
    for (const station of state.stationSort) {
      state.returnSort.unshift(station);
      console.log("站点信息", state.returnSort);
    }
  },
  //添加站点按钮
  addStation: (state, stationIndex) => {
    state.stationSort.splice(stationIndex + 1, 0, state.stationSelect);
  },
  //删除站点按钮
  deleteStation: (state, stationIndex) => {
    state.stationSort.splice(stationIndex, 1);
  },
  //一条线路的信息
  addOneRoadInfo: (state, oneRoadInfo) => {
    state.oneRoadInfo = oneRoadInfo;
    state.oneSchedule = oneRoadInfo.scheduleList;
    },
  //鼠标选择的站点保存
  stationSelect:(state, e) =>{
    state.stationSelect = {xPoint: '', yPoint: '', stationName: '', stationId: ''};
    state.stationSelect.xPoint= e.dataItem.geometry.coordinates[0];
    state.stationSelect.yPoint= e.dataItem.geometry.coordinates[1];
    state.stationSelect.stationName= e.dataItem.properties.text;
    state.stationSelect.stationId= e.dataItem.properties.stationId;
    // console.log("vuex", state.stationSelect);
  },
  //确定按钮首站保存
  addFirstStation:(state) =>{
    state.stationSort[0] = state.stationSelect;
  },
  //确定按钮尾站保存
  addLastStation:(state)=>{
    if (state.stationSort[0].stationName!=''&& state.stationSort[0].stationName !=null){
      if (state.stationSort.length>1){
        if (state.stationSort[state.stationSort.length - 1].stationName!= state.stationSelect.stationName){
          state.stationSort.push(state.stationSelect);
        }
      }else if (state.stationSort.length==1){
        state.stationSort.push(state.stationSelect);
      }
    }else{
      alert("请先选择首站点");
    }
    // console.log("vuex", state.stationSort);
  },
  //地点选择
  addLocal:(state, point)=>{
    state.localSelect={};
    state.localSelect= {xPoint: point.lng,yPoint: point.lat };
    // console.log("localSelect===========", state.localSelect);
  }

}

const action={

}


export default {
  namespaced: true,
  state,
  mutations,
  action
}
