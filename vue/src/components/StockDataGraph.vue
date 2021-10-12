<template>
    <div>
        <div class="chartContainer">
        <v-chart class="chart" :option ="option" />
        </div>
        <div class="timescale">
            <input type="radio" id="day" value="day" v-model="timescale">
            <label for="day">day</label>
            <input type="radio" id="week" value="week" v-model="timescale">
            <label for="week">week</label>
            <input type="radio" id="month" value="month" v-model="timescale">
            <label for="month">month</label>
            <input type="radio" id="year" value="year" v-model="timescale">
            <label for="year">year</label>
        </div>
    </div>
</template>

<script>
import stockService from '../services/StockService'
import { use } from "echarts/core";
import { CanvasRenderer } from "echarts/renderers";
import { LineChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent
} from "echarts/components";
import VChart from "vue-echarts";
// import { ref, defineComponent } from "vue";

use([
  CanvasRenderer,
  LineChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent
]);

export default {
    name: "stock-data-graph",
    components: {
        VChart,
    },
    props: ['ticker'],
    data() {
        return {
            timescale: 'week',
            option: {
                 title: {
                    show: false,
                    text: this.ticker + " Stock Value",
                    left: "center",
                    textStyle: {
                        fontSize: 24,
                        fontFamily: 'Poppins',
                        color: '#eee',
                    }
                },
                xAxis: {
                    type: 'category',
                    data: [],
                    show: false,
                },
                yAxis: {
                    type: 'value',
                    yAxisIndex: 1,
                    min: '',
                    max: '',
                },
                axisPointer: {
                    show: true,
                    snap: true,
                    label: {
                        fontFamily: 'Poppins',
                    }
                },
                series: [
                    {
                        data: [],
                        type: 'line',
                        color: '#ff4f79',
                    }
                ],
                backgroundColor: '#454545',
                textStyle: {
                    color: '#eee',
                    fontFamily: 'Poppins',
                }
            }
        }
    },
    created() {
        this.getData();
    },
    watch: {
        timescale: function () {
            this.getData();
        } 
    },
    methods: {
        getData() {
            let today = new Date();
            let begin; 
            let value;
            
            if(this.timescale == 'day') {
                value = 5;
                begin = new Date(today.getFullYear(), today.getMonth(), today.getDate())
            }
            else if(this.timescale == 'week') {
                value = 60;
                begin = new Date(today.getFullYear(), today.getMonth(), today.getDate() - 7, today.getHours(), today.getMinutes(), today.getSeconds(), today.getMilliseconds());
            } 
            else if (this.timescale == 'month') {
                value = 'D';
                begin = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate(), today.getHours(), today.getMinutes(), today.getSeconds(), today.getMilliseconds());
            } else if (this.timescale == 'year') {
                value = 'D';
                begin = new Date(today.getFullYear() - 1, today.getMonth(), today.getDate(), today.getHours(), today.getMinutes(), today.getSeconds(), today.getMilliseconds())
            }

            let unixTsBegin = Math.round(begin.getTime() / 1000);
            let unixTsToday = Math.round(today.getTime() / 1000);

            stockService.getHistoricalStockData(this.ticker, value, unixTsBegin, unixTsToday)
                .then(result => {
                    let values = [];
                    let timestamps = [];
                    let minValue = result.data[0].value;
                    let maxValue = result.data[0].value;

                    for(let i = 0; i < result.data.length; i++) {
                        let currentValue = result.data[i].value;
                        values.push(currentValue);

                        let currentTimestamp = result.data[i].timestamp;
                        let date = new Date(currentTimestamp * 1000).toString().substring(0, 24);
                        timestamps.push(date);
                        
                        if(currentValue < minValue) {
                            minValue = currentValue;
                        } else if (currentValue > maxValue) {
                            maxValue = currentValue;
                        }
                    }
                    
                    minValue = Math.floor(minValue);
                    maxValue = Math.ceil(maxValue);
                    
                    this.option.yAxis.min = minValue;
                    this.option.yAxis.max = maxValue;

                    this.option.series[0].data = values;

                    this.setColor(values[0], values[values.length-1]);

                    this.option.xAxis.data = timestamps;
               });

        },
        setColor(startValue, endValue) {
            if(endValue > startValue) {
                this.option.series[0].color = '#9bc53d';
            } else {
                this.option.series[0].color = '#ff4f79';
            }
        }
    },

}
</script>

<style scoped>

    .chartContainer {
        width: 100%;
        display: flex;
        align-content: center;
        justify-content: center;
    }

    .chart {
        width: 500px;
        height: 280px;
    }

    input[type=radio] {
        opacity: 0;
        position: fixed;
        width: 0;
    }

    .timescale {
        display: flex;
        justify-content: space-evenly;
        margin-bottom: 3rem;
    }

    .timescale label {
        display: inline-block;
        padding: 0.1rem 0.5rem;
        width: 4rem;
        text-align: center;
        font-family: 'Poppins', sans-serif;
        font-size: 0.8rem;
        border: 2px solid var(--background-color);
        border-radius: 8rem;
        cursor: pointer;
        transition: 0.4s;
    }

    .timescale label:hover {
        background-color: var(--background-color);
        color: var(--color-lighter);
    }

    .timescale input[type="radio"]:checked + label {
        background-color: var(--background-color);
        color: var(--color-lighter);
        font-weight: bold;
    }

</style>