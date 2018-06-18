<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:patientPage>

  <div class="row">
    <h1>Morning</h1>
    <table class="dailyMedTable table table-striped table-bordered">
      <tbody>
      <tr>
        <td style="width: 15%">
          <img src="https://img.tesco.com/Groceries/pi/718/5000158100718/IDShot_540x540.jpg" height="150px">
        </td>
        <td style="width: 60%">
          <div class="pillbox">

            <h2>Ibuprofen  <small>1 pill</small></h2>
            <h4>Instructions: Take after food</h4>
          </div>
        </td>
        <td style="width: 25%">
          <button type="button" class="btn btn-success btn-block btn-lg" onclick="openRecordModal(0)">Record</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
  <div class="row">
    <h1>Afternoon</h1>
    <table class="dailyMedTable table table-striped table-bordered">
      <tbody>
      <tr>
        <td style="width: 15%">
          <img src="https://img.tesco.com/Groceries/pi/718/5000158100718/IDShot_540x540.jpg" height="150px">
        </td>
        <td style="width: 60%">
          <div class="pillbox">

            <h2>Ibuprofen  <small>1 pill</small></h2>
            <h4>Instructions: Take after food</h4>
          </div>
        </td>
        <td style="width: 25%">
          <button type="button" class="btn btn-success btn-block btn-lg" onclick="openRecordModal(1)">Record</button>
        </td>
      </tr>
      <tr>
        <td style="width: 15%">
          <img src="https://img.tesco.com/Groceries/pi/718/5000158100718/IDShot_540x540.jpg" height="150px">
        </td>
        <td style="width: 60%">
          <div class="pillbox">

            <h2>Alprazolam  <small>2 pills</small></h2>
            <h4>Instructions: Take after food.</h4>
          </div>
        </td>
        <td style="width: 25%">
          <button type="button" class="btn btn-success btn-block btn-lg" onclick="openRecordModal(2)">Record</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
  <div class="row">
    <h1>Night</h1>
    <table class="dailyMedTable table table-striped table-bordered">
      <tbody>
      <tr>
        <td style="width: 15%">
          <img src="https://img.tesco.com/Groceries/pi/718/5000158100718/IDShot_540x540.jpg" height="150px">
        </td>
        <td style="width: 60%">
          <div class="pillbox">

            <h2>Ibuprofen  <small>1 pill</small></h2>
            <h4>Instructions: Take after food</h4>
          </div>
        </td>
        <td style="width: 25%">
          <button type="button" class="btn btn-success btn-block btn-lg" onclick="openRecordModal(3)">Record</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>

  <div id="recordModal" class="modal">
    <div class="modal-content">
      <h2>Please confirm.</h2><br>
      <div class="row">
        <div class="col-sm-6">
          <button type="button" class="btn btn-success btn-block btn-lg" onclick="">Confirm</button>
        </div>
        <div class="col-sm-6">
          <button type="button" class="btn btn-default btn-block btn-lg"  onclick="closeRecordModal()">Cancel</button>
        </div>
      </div>
    </div>
  </div>

  <script>
      // Get the modal
      var recordModal = document.getElementById('recordModal');

      // Get the button that opens the modal
      function openRecordModal() {
          recordModal.style.display = "block";
      }

      // When the user clicks on <span> (x), close the modal
      function closeRecordModal() {
          recordModal.style.display = "none";
      }
      // When the user clicks anywhere outside of the modal, close it
      window.onclick = function(event) {
          if (event.target == addModal) {
              recordModal.style.display = "none";
          }
      }
  </script>
</t:patientPage>
