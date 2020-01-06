pragma solidity ^0.4.24;

contract FincialChain {
    struct User {
        address uid;
        uint[] iouFrom;
        uint[] iouTo;
        uint limit;
    }

    struct Iou {
        uint iid;
        address userFrom;
        address userTo;
        uint amount;
        bool isValid;
    }

    mapping (address => User) users;
    mapping (uint => Iou) ious;
    uint curIouNum;

    // Original Operation
    function addIou(address _from, address _to, uint _amount) private returns (uint){
        Iou memory iou = Iou({
            iid:++curIouNum,
            userFrom:_from,
            userTo:_to,
            amount:_amount,
            isValid:true
            });

        ious[curIouNum] = iou;

        users[_from].iouFrom.push(curIouNum);
        users[_to].iouTo.push(curIouNum);

        users[_from].limit -= _amount;
        users[_to].limit += _amount;

        return curIouNum;
    }

    // Original Operation
    function delIou(uint _iid) private {
        ious[_iid].isValid = false;

        users[ious[_iid].userFrom].limit += ious[_iid].amount;
        users[ious[_iid].userTo].limit -= ious[_iid].amount;
    }

    // ------------------------------------------------------------------------------

    // 无论是开借条还是分裂借条，对上游无影响，自己亏了，下游赚了，所以自己不会乱开借条
    function generateIou(address _from, address _to, uint _amount) public returns (uint){
        require(msg.sender == _from);
        require(_amount > 0);
        require(users[_from].limit >= _amount);

        return addIou(_from, _to, _amount);
    }

    function splitIou(address _from, address _to, uint _iid, uint _amount) public returns (uint){
        require(msg.sender == _from);
        require(ious[_iid].isValid == true);
        require(ious[_iid].userTo == _from);
        require(ious[_iid].amount >= _amount);

        delIou(_iid);

        Iou memory iou = ious[_iid];
        addIou(iou.userFrom, _to, _amount);

        if (iou.amount > _amount){
            return addIou(iou.userFrom, iou.userTo, iou.amount - _amount);
        }
        return 0;
    }

    function reqIou(address _from, address _to, uint _iid, uint _amount) public returns (uint){
        require(msg.sender == _from);
        require(ious[_iid].isValid == true);
        require(ious[_iid].userTo == _from);
        require(ious[_iid].amount >= _amount);

        return generateIou(_from, _to, _amount);
    }

    function payIou(address _from, address _to, uint _iid, uint _amount) public returns (uint){
        require(msg.sender == _from);
        require(ious[_iid].isValid == true);
        require(ious[_iid].userFrom == _from);
        require(ious[_iid].userTo == _to);
        require(ious[_iid].amount <= _amount);

        users[ious[_iid].userFrom].limit += _amount - ious[_iid].amount;

        delIou(_iid);

        return _iid;
    }

    // ------------------------------------------------------------------------------

    function setUserLimit(address addr, uint _limit) public {
        users[addr].limit = _limit;
    }

    function getUserLimit(address addr) public view returns (uint){
        return users[addr].limit;
    }

    function getIou(uint _iid) public view returns (uint, address, address, uint, bool){
        Iou memory iou = ious[_iid];
        return (iou.iid, iou.userFrom, iou.userTo, iou.amount, iou.isValid);
    }

    function getAllIous(address addr) public view returns (uint[]){
        uint[] memory ff = users[addr].iouFrom;
        uint[] memory tt = users[addr].iouTo;

        uint[] memory arr = new uint[](ff.length + tt.length);

        uint k=0;
        if (ff.length > 0) for (uint i=0; i<ff.length; i++)arr[k++] = ff[i];
        if (tt.length > 0) for (uint j=0; j<tt.length; j++)arr[k++] = tt[j];

        return arr;
    }

    function getCurIouNum() public view returns(uint) {
        return curIouNum;
    }
}

