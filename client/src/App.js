import React, {Component} from 'react';
import './App.css';

class MainComponent extends Component
{
  handleOnClick = () => {
    console.log("Click")
  };

  render(){
    return(
      <div className={"App"}>
        <header className={"App-header"}>
          <button onClick = {this.handleOnClick}>
            Gmail login
          </button>
        </header>
      </div>
    )
  }
}

export default MainComponent;