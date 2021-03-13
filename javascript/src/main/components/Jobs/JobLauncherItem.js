import React from "react";

const JobLauncherItem = ({ job }) => {
  console.log("job=", job);
  return (
    <tr key={job.key}>
      <td>
        <span
          className="job_name"
          data-toggle="tooltip"
          title={`${job.description}`}
        >
          {job.key}
        </span>
      </td>
      <td>TODO: Put last run time here</td>
      <td>TODO: Put link to run here</td>
    </tr>
  );
};

export default JobLauncherItem;
