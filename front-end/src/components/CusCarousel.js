import React, { useState, useEffect, useContext } from "react";
import avatar from "../assets/avatar.png";
import { Image } from "antd";
import web4 from "../assets/web4.png";
import { AuthContext } from "../contexts/AuthContext";

import InfiniteScroll from "react-infinite-scroll-component";
import { Divider, Skeleton } from "antd";

import { Card, List } from "antd";
import axios from "axios";

const CusCarousel = (props) => {
  const { mainUser } = useContext(AuthContext);
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState([]);
  //Pagination
  const [page, setPage] = useState(0);
  const loadMoreData = () => {
    if (loading) {
      return;
    }
    setLoading(true);
    //Get friendlist from api with axios
    axios
      .get(
        `http://54.251.83.192:9001/friends/getFriendOfUser/` +
          mainUser.id +
          `?page=` +
          page +
          `&size=10`
      )
      .then((res) => {
        console.log("Content of api", res.data.data.content);
        setData([...data, res.data.data.content]);
        setLoading(false);
      })
      .catch((err) => {
        setLoading(false);
      });
    // fetch(
    //   //Fetch friendlist from api
    //   `http://54.251.83.192:9001/friends/getFriendOfUser/13?page=`+page+`&size=10`
    // )
    //   .then((res) => res.json())
    //   .then((body) => {
    //     console.log('Content of api',JSON.stringify(body.results));
    //     setData([...data.content, ...body.results]);
    //     setLoading(false);
    //   })
    //   .catch(() => {
    //     setLoading(false);
    //   });
  };
  useEffect(() => {
    loadMoreData();
  }, []);

  return (
    <InfiniteScroll
      dataLength={data.length}
      next={() => {
        setPage(page + 1);
        loadMoreData();
      }}
      hasMore={data.totalPages < 50}
      loader={
        <Skeleton
          avatar
          paragraph={{
            rows: 1,
          }}
          active
        />
      }
      endMessage={<Divider plain>It is all, nothing more 🤐</Divider>}
      scrollableTarget='scrollableDiv'
    >
      <div className='gap-8 lg:flex'>
        <List
          grid={{
            gutter: 16,
            column: 5,
          }}
          dataSource={data}
          renderItem={(item) =>
            // console.log('Item',item)
            // Filter item[0].users who id is different from userId
            item.map((data) =>
              data.users
                .filter((user) => user.id !== mainUser.id)
                .map((user) => (
                  <List.Item key={user.id}>
                    {props.props === "friends" ? (
                      <div className='flex-1 p-6 my-6 text-center shadow-lg rounded-xl dark:bg-white'>
                        <Image src={avatar} width={100} height={100} />
                        <h3 className='pt-8 pb-2 text-lg font-medium '>{user.username}</h3>
                        {/* View profile add */}
                        <p className='py-1 text-teal-500'>View Profile</p>
                      </div>
                    ) : (
                      <div className='flex-1 basis-1/3'>
                        <Image
                          className='object-cover rounded-lg'
                          width={"100%"}
                          height={"100%"}
                          layout='responsive'
                          src={web4}
                        />
                      </div>
                    )}
                  </List.Item>
                ))
            )
          }
        />
      </div>
    </InfiniteScroll>
  );
};

export default CusCarousel;
