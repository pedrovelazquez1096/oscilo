import axios from 'axios';
import React, {useState, useEffect} from "react";
import {
  Tooltip,
  Legend, CartesianGrid, Line, LineChart, XAxis, YAxis, ReferenceLine
} from "recharts";

function Graph() {
  const [data, setData] = useState({});

  const [minX, setMinX] = useState({});
  const [minY, setMinY] = useState({});

  useEffect(() =>{
    getData();
  });

  const getData = () => {
    let data = [];
    axios.get("http://localhost:8080/api/v1.0/lectura/all")
    .then(res => {
      for(const dataObj of res.data){
        data.push({
          x: dataObj.xvalue,
          y: dataObj.yvalue
        });
      }
      setData(data);
      setMinX(Math.min(...data.map((p) => p.y)));
      setMinY(Math.min(...data.map((p) => p.x)));
    });
  }

  const CustomTooltip = ({ active, payload, label }) => {
    if (active && payload && payload.length) {
      return (
        <div className="custom-tooltip">
          <p className="label">{`${parseFloat(payload[0].value)} volts`}</p>
        </div>
      );
    }
  
    return null;
  };
  
  return (
        <LineChart
          width={900}
          height={700}
          margin={{
            top: 50,
            right: 30,
            left: 20,
            bottom: 5,
          }}
        >
          <CartesianGrid strokeDasharray="3 3" />

          <YAxis
            dataKey="y"
            domain={['auto', 'auto']}
            type="number"
            interval={0}
            label={{
              value: `Voltaje`,
              style: { textAnchor: 'middle' },
              angle: -90,
              position: 'left',
              offset: 0,
            }}
            allowDataOverflow={true}
            strokeWidth={minX < 0 ? 0 : 1}
          />

          <XAxis
            dataKey="x"
            domain={['auto', 'auto']}
            interval={0}
            type="number"
            label={{
              key: 'Tiempo',
              value: 'Tiempo',
              position: 'bottom',
            }}
            allowDataOverflow={true}
            strokeWidth={minY < 0 ? 0 : 1}
          />

          {minY < 0 && (
            <ReferenceLine
              y={0}
              stroke="gray"
              strokeWidth={1.5}
              strokeOpacity={0.65}
            />
          )}
          {minX < 0 && (
            <ReferenceLine
              x={0}
              stroke="gray"
              strokeWidth={1.5}
              strokeOpacity={0.65}
            />
          )}

          <Tooltip content={<CustomTooltip />} />
          <Legend />
          <Line
            strokeWidth={2}
            data={data}
            dot={false}
            type="monotone"
            dataKey="y"
            
            stroke="#8884d8" 
            activeDot={{ r: 8 }}
          />
        </LineChart>

  );
}

export default Graph;
