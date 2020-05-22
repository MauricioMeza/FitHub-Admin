import {EventSettingsModel} from "@syncfusion/ej2-react-schedule";
import {DataManager, WebApiAdaptor} from "@syncfusion/ej2-data";

class classData {
    localData: EventSettingsModel = {
        dataSource: [{
            Id: 1,
            Subject: 'Meeting - 1',
            EndTime: new Date(2019, 0, 11, 6, 30),
            StartTime: new Date(2019, 0, 11, 4, 0),
            IsAllDay: false
        }]
    }

    remoteData = new DataManager({
        url: "https://js.syncfusion.com/demos/ejservices/api/Schedule/LoadData",
        adaptor: new WebApiAdaptor,
        crossDomain: true
    });
    /*private data: object [] = [{
        {
        Id: 1,
        Subject: 'Meeting - 1',
        StartTime: new Date(2018, 1, 15, 10, 0),
        EndTime: new Date(2018, 1, 16, 12, 30),
        IsAllDay: false
        },
        {
            Id: 2,
            Subject: 'Meeting - 1',
            StartTime: new Date(2018, 1, 15, 10, 0),
            EndTime: new Date(2018, 1, 16, 12, 30),
            IsAllDay: false
        }
        
    }]*/
}

export default new classData();