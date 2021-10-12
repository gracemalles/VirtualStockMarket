<template>
  <div class="container">
    <v-chart class="chart" :option ="option" />
  </div>
</template>

<script>
import playerService from '../services/PlayerService'
import playerHistoryService from '../services/PlayerHistoryService'
import { use } from "echarts/core";
import { CanvasRenderer } from "echarts/renderers";
import { LineChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent
} from "echarts/components";
import VChart, { THEME_KEY } from "vue-echarts";

use([
  CanvasRenderer,
  LineChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent
]);
export default {
  name: "LeaderStats",
  components: {
    VChart
  },
  provide: {
    [THEME_KEY]: "dark"
  },
  data() {
    return {
      players: [],
      playerHistories: []
    }
  },
  computed: {
      sortedPlayerHistories() {
        let returnArray = this.playerHistories;
        returnArray.sort((a,b) => {
          const aDate = new Date(a.localDate + 'T' + a.localTime + '.000Z');
          const bDate = new Date(b.localDate + 'T' + b.localTime + '.000Z');
          return aDate.getTime() -bDate.getTime();
      });
      return returnArray;
      },
        
      option() {
        let usernames = [];
        let seriesValues = [];
        let xValues = [];
        let minValue = 1000000000;
        let maxValue = 0;
          if (this.sortedPlayerHistories) {
            for (let i = 0; i < this.players.length; i++) {
              usernames.push(this.players[i].username);
              let currentSeries = {
                name: this.players[i].username,
                type: "line",
              }
              let dataValue = [];
              for (let j = 0; j < this.sortedPlayerHistories.length; j++) {
                if (this.players[i].id == this.sortedPlayerHistories[j].playerId) {
                  if (this.sortedPlayerHistories[j].portfolioValue > maxValue) {
                    maxValue = this.sortedPlayerHistories[j].portfolioValue;
                  }
                  if (this.sortedPlayerHistories[j].portfolioValue < minValue) {
                    minValue = this.sortedPlayerHistories[j].portfolioValue;
                  }
                  dataValue.push(this.sortedPlayerHistories[j].portfolioValue);
                    let date = this.sortedPlayerHistories[j].localTime;
                    if(!xValues.includes(date)) xValues.push(date);
                }
              }
              currentSeries.data = dataValue;
              seriesValues.push(currentSeries);
            }
          minValue = minValue - 250;
          maxValue = maxValue + 250;
          }
        return {
        title: {
          show: false,
          text: "Leader Stats",
          left: "right"
        },
        tooltip: {
          trigger: "item",
          formatter: "<strong>{a}</strong> <br/>{b} : ${c}"
        },
        legend: {
            orient: "horizontal",
            left: "left",
            data: usernames
                
        },
        textStyle: {
          fontSize: 8,
          fontFamily: 'Poppins',
          color: '#eee',
        },
        axisPointer: {
            show: true,
            snap: true,
            label: {
                fontFamily: 'Poppins',
            }
        },
        backgroundColor: '#454545',
        xAxis: {
            type: 'category',
            fontSize: 8,
            data: xValues,
            axisLine: {show: true},
            axisTick: {show: true},
            axisLabel: {
              show: true,
              interval: 25
            }
        },
        yAxis: {
            type: 'value',
            name: 'Portfolio Value',
            fontSize: 8,
            min: minValue,
            max: maxValue,
            axisLabel: {
              show: false,
              interval: 10000
            }, 
            position: 'right',

        },
        series: seriesValues,
        color: ['#FF4F79', '#5438DC', '#C0D8E0', '#9BC53D', '#BBB6DF']
      }
    }
  },
  props: ['gameId'],
  created() {
    playerService.getPlayersByGame(this.gameId).then(response => {
        this.players = [];
        for (let i = 0; i < response.data.length; i++) {
          if(response.data[i].game_status != 'Rejected') {
            this.players.push(response.data[i]);
          }
        }
    }).then(() => {
        for (let i = 0; i < this.players.length; i++) {
          playerHistoryService.getHistoryByPlayerId(this.players[i].id)
            .then((response) => {
              for (let j = 0; j < response.data.length; j++) {
                this.playerHistories.push(response.data[j]);
              }
            });
        }  
    })
  },
  watch: {
    countdown: function() {
      setTimeout(() => {
        for (let i = 0; i < this.players.length; i++) {
          playerHistoryService.getHistoryByPlayerId(this.players[i].id)
            .then((response) => {
              for (let j = 0; j < response.data.length; j++) {
                this.playerHistories.push(response.data[j]);
              }
            });
        }
      }, 60000);
    }
  }
}
</script>

<style scoped>
  .chart {
    padding: 1rem;
    width: 25vw;
    height: 20vw;
  }
  
  .chart div {
    width: 25vw;
    height: 20vw;
  }

  .container {
    width: 100%;
    display: flex;
    align-content: center;
    justify-content: center;
  }

</style>