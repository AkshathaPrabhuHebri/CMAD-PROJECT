import React, { Component } from 'react';

class LogViewer extends Component {
  render(){
    return (
      <div>
        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">Timestamp</th>
              <th scope="col">Severity</th>
              <th scope="col">Facility</th>
              <th scope="col">Message</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">Nov 03 2003 10:12:12</th>
              <td>Error</td>
              <td>kern</td>
              <td>Restart nginx server</td>
            </tr>
            <tr>
              <th scope="row">Nov 03 2003 10:12:12</th>
              <td>Error</td>
              <td>user</td>
              <td>Unable to run</td>
            </tr>
            <tr>
              <th scope="row">Nov 03 2003 10:12:12</th>
              <td>Info</td>
              <td>kern</td>
              <td>Unable to runr</td>
            </tr>
            <tr>
              <th scope="row">Nov 03 2003 10:12:12</th>
              <td>Error</td>
              <td>kern</td>
              <td>Restart nginx server</td>
            </tr>
            <tr>
              <th scope="row">Nov 03 2003 10:12:12</th>
              <td>Error</td>
              <td>user</td>
              <td>Unable to run</td>
            </tr>
            <tr>
              <th scope="row">Nov 03 2003 10:12:12</th>
              <td>Info</td>
              <td>kern</td>
              <td>Unable to runr</td>
            </tr>
          </tbody>
        </table>
        <nav aria-label="...">
          <ul class="pagination">
            <li class="page-item disabled">
              <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item active" aria-current="page">
              <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
            </li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
              <a class="page-link" href="#">Next</a>
            </li>
          </ul>
        </nav>
      </div>
      
    );
  }
}
export default LogViewer;





