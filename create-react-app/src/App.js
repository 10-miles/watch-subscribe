import React from 'react';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <button onClick = {() => {console.log("Click")}}>
          Gmail login
        </button>
      </header>
    </div>
  );
}

export default App;
