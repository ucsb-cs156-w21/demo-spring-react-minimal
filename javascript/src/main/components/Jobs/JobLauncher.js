import React from "react";
import JobLauncherItem from "main/components/Jobs/JobLauncherItem";

const JobLauncher = ({ jobs }) => {
  console.log("jobs=", jobs);
  return (
    <>
      <div className="panel panel-default">
        <div className="panel-heading">
          <div className="panel-title">Jobs</div>
        </div>
        <div className="panel-body">
          <table className="table">
            <thead>
              <tr>
                <th>Job Name</th>
                <th>Last Run</th>
                <th colSpan="1"></th>
              </tr>
            </thead>
            <tbody>
              {jobs &&
                jobs.map((job) => <JobLauncherItem key={job.key} job={job} />)}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default JobLauncher;
