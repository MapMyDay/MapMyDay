import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import { HashRouter, Route, Routes, NavLink, useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import Input from './components/Input';

function Home() {
  return (
    <div>
      <h2>Home</h2>
      Home...
    </div>
  );
}
var cnt=-1;
var contents = [
  {id:1, title: 'CNU', description: 'Here is CNU'},
  {id:2, title: 'STARBUCKS', description: 'Here is STARBUCKS'},
  {id:3, title: 'Ato', description: 'Here is Ato'},
]

const use_for = (arr) => {
  const result = [];

  for (let i = 0; i < arr.length; i++) {
    result.push(<p key={i}>
      {arr[i].id}
      &nbsp;
      {arr[i].title}
      &nbsp;
      <input type="button" value="DELETE" onClick={()=>{

      }}></input>
      
      </p>);
  }

  return result;
};

var selected = [];  
function Topic() {
  const [id, setId]=useState(null);
  var params = useParams();
  var topic_id = params.topic_id;
  var selected_topic = {
    title: 'Sorry',
    description: 'Not Found'
  };
  for(var i=0;i<contents.length;++i){
    if(contents[i].id === Number(topic_id)){
      selected_topic = contents[i];
      var dictObject = {}
      dictObject["id"] = contents[i].id;
      dictObject["title"] = contents[i].title;
      selected.push(dictObject);
      
      // selected_topic = contents[i];
      // var to_selected = {
      //   description: {selected_topic}
      // };
      // selected.push(to_selected);
      break;
    }
  }
  // console.log(params);
  // console.log(selected.length);
  ++cnt;
  const [s, setTopics] = useState([
    // {id:1, title: 'HTML', description: 'HTML is'},
    // {id:2, title: 'JS', description: 'JS is'},
    // {id:3, title: 'React', description: 'React is'}
  ])
  
  return (
    <div>
      <h3>{selected_topic.title + " Selected"}</h3>
      {/* {selected_topic.description} */}
      {use_for(selected)}
      <input type="button" value="DELETE" onClick={()=>{
        const newTopics = []
        // console.log(Number(selected[0].id))
        console.log("hello")
        // console.log(Number(topic_id))
        console.log(selected.length);
        for(let i=0;i<selected.length;++i){
          // console.log("i");
          if(selected[i].id !== Number(topic_id)){
            newTopics.push(selected[i]);
          }else{
            // console.log("why");
          }
          
        }
        console.log(newTopics.length);
        setTopics(newTopics);
        
        {use_for(s)};
      }}></input>
    </div>
  );
}
function Topics() {
  const Submit = () => {
    fetch('http://15.165.50.48:8000/api/test', {
        method : "GET",
        headers : {               
          "Content-Type":"application/json; charset=utf-8"
        },
        // headers: {
        //   Accept: "application/json",
        // },
        // body: JSON.stringify(selected)
        
    }).then((response)=>response.json())
      .then(response=>{
        console.log(response);
        
    });              
  };
//   useEffect(()=> {
//     fetch('http://192.168.45.175:3000/api/hello', {
//         method : "GET",
//         headers : {               
//           "Content-Type":"application/json; charset=utf-8"
//         },
//         headers: {
//           Accept: "application/json",
//         },
//         // body: JSON.stringify(selected)
        
//     }).then((response)=>response.json())
//       .then(response=>{
//         console.log(response);
        
//     });              
// }, []);
  var lis=[];
  // CountQueuingStrategy
  // console.log(contents.length);
  for(var i=0;i<contents.length;++i){
    lis.push(
      <li key={contents[i].id}>
        <NavLink to={"/topics/" + contents[i].id}>{contents[i].title}</NavLink>
        
        </li>
    );
  }
  
  return (
    <div>
      <h2>Places</h2>
      <ul>
        {lis}
        {/* <li><NavLink to="/topics/1">HTML</NavLink></li>
        <li><NavLink to="/topics/2">JS</NavLink></li>
        <li><NavLink to="/topics/3">React</NavLink></li> */}
        <p><input type="button"
          value="SUBMIT"
          onClick={Submit}  
          ></input>
          
        </p>
      </ul>
      <Routes>
        <Route path="/:topic_id" element={<Topic />} />
      </Routes>
    </div>
  );
}
function Contact() {
  return (
    <div>
      <h2>Contact</h2>
      Contact...
    </div>
  );
}

function App() {
  // const URL = `https://cors-anywhere.herokuapp.com/corsdemo`
  
  return (
    <div>
      <h1>Choose MAP MY DAY</h1>
      <ul>
        <li><NavLink to="/">Home</NavLink></li>
        <li><NavLink to="/topics">Places</NavLink></li>
        <li><NavLink to="/contact">Contact</NavLink></li>
      </ul>
      <Routes>
        <Route path="/" element={<Home / >} />
        <Route path="/topics/*" element={<Topics / >} />
        <Route path="/contact" element={<Contact / >} />
        <Route path="/*" element={'Not Found'} />
      </Routes>
    </div>
  );
}
ReactDOM.createRoot(document.getElementById('root')).render(<HashRouter><App /></HashRouter>);


reportWebVitals();
