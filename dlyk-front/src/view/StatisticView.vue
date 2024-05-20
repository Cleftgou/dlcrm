<template>

  <!--概览统计-->
  <el-row>
    <el-col :span="6">
      <el-statistic :value="summaryData.effectiveActivityCount">
        <template #title>
          <div style="display: inline-flex; align-items: center">
            市场活动
          </div>
        </template>
        <template #suffix>
          /{{ summaryData.totalActivityCount }}
          <el-icon style="vertical-align: -0.125em">
            <DataAnalysis />
          </el-icon>
        </template>
      </el-statistic>
    </el-col>

    <el-col :span="6">
      <el-statistic title="线索总数" :value="summaryData.totalClueCount" >
        <template #suffix>
          <el-icon style="vertical-align: -0.125em">
            <MessageBox />
          </el-icon>
        </template>
      </el-statistic>
    </el-col>

    <el-col :span="6">
      <el-statistic title="客户总数" :value="summaryData.totalCustomerCount" >
        <template #suffix>
          <el-icon style="vertical-align: -0.125em">
            <User />
          </el-icon>
        </template>
      </el-statistic>
    </el-col>

    <el-col :span="6">
      <el-statistic :value="summaryData.successTranAmount">
        <template #title>
          <div style="display: inline-flex; align-items: center">
            交易总额
          </div>
        </template>
        <template #suffix>
          /{{ summaryData.totalTranAmount }}
          <el-icon style="vertical-align: -0.125em">
            <Money />
          </el-icon>
        </template>
      </el-statistic>
    </el-col>
  </el-row>

  <!--销售漏斗图统计图表-->
  <div id="saleFunnelChart" style="width: 48%;height:350px;margin-top: 30px;float: left">
    销售漏斗图
  </div>

  <!--线索来源饼图表-->
  <div id="sourcePieChart" style="width: 48%;height:350px;margin-top: 30px;float: left">
    线索来源饼图
  </div>

  <!--为ECharts定义一个div，指定好宽度和高度，用来显示市场活动统计柱状图-->
  <div id="activityBarChart" style="clear: both; width: 95%; height:350px; margin:15px; text-align: center;">
    市场活动统计柱状图
  </div>

  <div id="clueLineChart" style="clear: both; width: 95%; height:350px; margin:15px; text-align: center;">
    线索统计折线图
  </div>

</template>

<script>
import "../http/httpRequest.js";
import axios from "axios";
import * as echarts from 'echarts';

export default {
  props: ['nowUser'],
  name: "StatisticView",

  data() {
    return {
      //概览统计对象
      summaryData: {}
    }
  },

  mounted() {
    this.loadSummary();
    //加载销售漏斗图
    this.loadSaleFunnelChart();
    //加载线索来源饼图
    this.loadSourcePieChart();
    //加载市场活动统计柱状图
    this.loadActivityBarChart();
    //线索统计折线图
    this.loadClueLineChart();
  },

  methods: {
    //加载概览统计数据
    loadSummary() {
      axios.get("/api/summary/data").then(response => {
        if (response.data.code === 200) {
          this.summaryData = response.data.data;
        }
      })
    },

    //加载销售漏斗图
    loadSaleFunnelChart() {
      //先查数据，再渲染图，顺序不能错
      axios.get("/api/saleFunnel/data").then(response => {
        if (response.data.code === 200) {

          let saleFunnelData = response.data.data;

          //拿到页面上渲染图表的dom元素
          var chartDom = document.getElementById('saleFunnelChart');
          //使用echarts元素对图表进行初始化（空白的图表）
          var myChart = echarts.init(chartDom);
          //配置可选项（唯一需要改的，查看配置项手册即可）
          var option = {
            //标题组件，包含主标题和副标题
            title: {
              //主标题文本，支持使用 \n 换行。
              text: '销售漏洞图',
              left: 'center',
              top: 'bottom'
            },
            //提示框组件
            tooltip: {
              //触发类型。
              trigger: 'item',
              //提示框浮层内容格式器{a}（系列名称），{b}（数据项名称），{c}（数值）, {d}（百分比）
              formatter: '{a} <br/>{b} : {c}'
            },
            //工具栏（右上角）
            toolbox: {
              //各工具配置项。
              feature: {
                //数据视图工具，可以展现当前图表所用的数据，编辑后可以动态更新
                dataView: {
                  //是否不可编辑（只读）
                  readOnly: false },
                //配置项还原。
                restore: {},
                //保存为图片
                saveAsImage: {}
              },
              top : 'bottom'
            },
            //图例组件。
            legend: {
              data: ['线索', '客户', '交易', '成交'],
            },
            //系列名称
            series: [
              {
                name: '销售漏斗数据统计',
                //图标类型funnel漏斗图
                type: 'funnel',
                //漏斗图组件离容器左侧的距离。
                left: '10%',
                //漏斗图组件离容器上侧的距离
                top: 60,
                //漏斗图组件离容器下侧的距离
                bottom: 60,
                width: '80%',
                min: 0,
                max: 100,
                minSize: '0%',
                maxSize: '100%',
                sort: 'descending',
                gap: 2,
                label: {
                  show: true,
                  position: 'inside'
                },
                labelLine: {
                  length: 10,
                  lineStyle: {
                    width: 1,
                    type: 'solid'
                  }
                },
                itemStyle: {
                  borderColor: '#fff',
                  borderWidth: 1
                },
                emphasis: {
                  label: {
                    fontSize: 20
                  }
                },
                //数据项名称
                //系列中的数据内容数组，数组项可以为单个数值，也可以是对象值
                // data: [
                //   { value: 20, name: '成交' },
                //   { value: 60, name: '交易' },
                //   { value: 80, name: '客户' },
                //   { value: 100, name: '线索' }
                // ]
                data : saleFunnelData
              }
            ]
          };
          //如果配置了可选项，就把可选项配置到空白的图表对象当中
          option && myChart.setOption(option);
        }
      })
    },

    //加载线索来源饼图
    loadSourcePieChart() {
      axios.get("/api/sourcePie/data").then(response => {
        if (response.data.code === 200) {
          let sourcePieData = response.data.data;
          var chartDom = document.getElementById('sourcePieChart');
          var myChart = echarts.init(chartDom);
          var option = {
            title: {
              text: '线索来源统计',
              left: 'center',
              top: 'bottom',
            },
            tooltip: {
              trigger: 'item'
            },
            //工具栏（右上角）
            toolbox: {
              //各工具配置项。
              feature: {
                //数据视图工具，可以展现当前图表所用的数据，编辑后可以动态更新
                dataView: {
                  //是否不可编辑（只读）
                  readOnly: false },
                //配置项还原。
                restore: {},
                //保存为图片
                saveAsImage: {},
              },
              top : 'bottom'
            },
            //图例
            legend: {
              orient: 'horizontal',
              left: 'center'
            },
            series: [
              {
                name: '线索来源统计',
                type: 'pie',
                //半径
                radius: '60%',
                //数据项
                // data: [
                //   { value: 1048, name: 'Search Engine' },
                //   { value: 735, name: 'Direct' },
                //   { value: 580, name: 'Email' },
                //   { value: 484, name: 'Union Ads' },
                //   { value: 300, name: 'Video Ads' }
                // ],
                data: sourcePieData,
                //高亮状态的扇区和标签样式
                emphasis: {
                  itemStyle: {
                    //图形阴影的模糊大小
                    shadowBlur: 10,
                    //阴影水平方向上的偏移距离
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
                }
              }
            ]
          };
          option && myChart.setOption(option);
        }
      })

    },

    //市场活动统计柱状图
    loadActivityBarChart() {
      axios.get("/api/activityBar/data").then(response => {
        if (response.data.code === 200) {
          let activityBarData = response.data.data;

          const months = [];
          const counts = [];

          for (let i = 0; i < activityBarData.length; i++) {
            months.push(activityBarData[i].month);
            counts.push(activityBarData[i].count);
          }

          var chartDom = document.getElementById('activityBarChart');
          var myChart = echarts.init(chartDom);
          var option = {
            title: {
              text: '市场活动数量统计',
              left: 'center',
              top: 'bottom',
            },
            xAxis: {
              type: 'category',
              //data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
              data: months
            },
            yAxis: {
              type: 'value',
            },
            //工具栏（右上角）
            toolbox: {
              //各工具配置项。
              feature: {
                //数据视图工具，可以展现当前图表所用的数据，编辑后可以动态更新
                dataView: {
                  //是否不可编辑（只读）
                  readOnly: false },
                //配置项还原。
                restore: {},
                //保存为图片
                saveAsImage: {},
              },
              top : 'bottom'
            },
            //提示框组件。
            tooltip: {
              //触发类型。
              trigger: 'axis',
            },
            series: [
              {
                //data: [120, 200, 150, 80, 70, 110, 130],
                data: counts,
                barWidth: 40,
                type: 'bar',
                emphasis: {
                  itemStyle: {
                    //图形阴影的模糊大小
                    shadowBlur: 10,
                    //阴影水平方向上的偏移距离
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
                }
              }
            ]
          };
          option && myChart.setOption(option);
        }
      })

    },

    //折线图
    loadClueLineChart(){
      axios.get("/api/clueLine/data").then(response => {
        let activityBarData = response.data.data;

        const months = [];
        const counts = [];

        for (let i = 0; i < activityBarData.length; i++) {
          if (activityBarData[i].month === null){
            activityBarData[i].month = '未知'
          }
          months.push(activityBarData[i].month);
          counts.push(activityBarData[i].count);
        }

        var chartDom = document.getElementById('clueLineChart');
        var myChart = echarts.init(chartDom);
        var option = {
          xAxis: {
            type: 'category',
            data: months,
          },
          yAxis: {
            type: 'value'
          },
          title: {
            text: '线索统计图',
            left: 'center',
            top: 'bottom',
          },
          //工具栏（右上角）
          toolbox: {
            //各工具配置项。
            feature: {
              //数据视图工具，可以展现当前图表所用的数据，编辑后可以动态更新
              dataView: {
                //是否不可编辑（只读）
                readOnly: false },
              //配置项还原。
              restore: {},
              //保存为图片
              saveAsImage: {},
            },
            top : 'bottom'
          },
          //提示框组件。
          tooltip: {
            //触发类型。
            trigger: 'axis',
          },
          series: [
            {
              data: counts,
              type: 'line'
            }
          ]
        };
        option && myChart.setOption(option);
      })

    }
  }
}
</script>

<style scoped>
.el-row{
  text-align: center;
}
</style>