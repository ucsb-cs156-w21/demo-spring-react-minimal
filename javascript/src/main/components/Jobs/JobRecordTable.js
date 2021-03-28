import React from "react";
import BootstrapTable from "react-bootstrap-table-next";
import paginationFactory, {
  PaginationProvider,
  PaginationListStandalone,
  SizePerPageDropdownStandalone,
} from "react-bootstrap-table2-paginator";

import useSWR from "swr";
import { useAuth0 } from "@auth0/auth0-react";
import { fetchWithToken } from "../../utils/fetch";
import dateformat from "dateformat";

// const JobRecordTable = ({ data }) => {
const JobRecordTable = ({
  data,
  page,
  sizePerPage,
  onTableChange,
  totalSize,
}) => {
  const dateFormatter = (cell, _row, _rowIndex) => {
    try {
      if (cell === "" || cell === 0 || cell == null) return "";
      const date = new Date(cell);
      return dateformat(new Date(cell), "yyyy/mm/dd HH:MM:ss.l Z");
    } catch (e) {
      return "-";
    }
  };

  const columns = [
    {
      dataField: "id",
      text: "id",
    },
    {
      dataField: "key",
      text: "Key",
    },
    {
      dataField: "params",
      text: "Params",
    },
    {
      dataField: "scheduled",
      text: "Scheduled",
      formatter: dateFormatter,
    },
    {
      dataField: "started",
      text: "Started",
      formatter: dateFormatter,
    },
    {
      dataField: "completed",
      text: "Completed",
      formatter: dateFormatter,
    },
    {
      dataField: "message",
      text: "Message",
    },
  ];

  // console.log("JobRecordTable jobrecords=",jobrecords);

  const pageSizeOptions = [
    {
      text: "5",
      value: 5,
    },
    {
      text: "10",
      value: 10,
    },
  ];

  return (
    <PaginationProvider
      pagination={paginationFactory({
        custom: true,
        page,
        sizePerPage,
        totalSize,
        sizePerPageList: pageSizeOptions,
      })}
    >
      {({ paginationProps, paginationTableProps }) => (
        <div>
          <div class="d-flex">
            <SizePerPageDropdownStandalone
              className="mr-5"
              {...paginationProps}
            />
            <PaginationListStandalone {...paginationProps} />
          </div>
          <BootstrapTable
            remote
            keyField="id"
            data={(data && data.content) || []}
            columns={columns}
            onTableChange={onTableChange}
            {...paginationTableProps}
          />
        </div>
      )}
    </PaginationProvider>
  );
};
export default JobRecordTable;

/* <BootstrapTable
        remote
        keyField='id'
        data={data && data.content || []}
        onTableChange={ onTableChange }
        columns={columns} /> */
