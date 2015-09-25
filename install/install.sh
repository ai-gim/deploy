# cat install.sh 
#!/bin/bash
install_dir=/usr/local/lib/gim
if [[ -e $install_dir ]];then
    rm -rf $install_dir/deploy
else
    mkdir -p $install_dir
fi
sed -n '1,/^exit 0$/!p' $0 >$install_dir/deploy.tar.gz

cd $install_dir
tar -xzvf deploy.tar.gz

if [[ -e /etc/gim ]];then
    rm -rf /etc/gim/db.conf
    rm -rf /etc/gim/deploy.conf
else
    mkdir /etc/gim
fi

cd $install_dir/deploy
cp conf/* /etc/gim
cp init.d/gim-deploy /etc/rc.d/init.d

chmod 755 bin/*
chmod 755 /etc/rc.d/init.d/gim-deploy
chkconfig gim-deploy on

path=$(pwd)
mysql -uroot << EOF
use gim
source $path/sql/create_objects.sql;
EOF

rm -rf $install_dir/deploy.tar.gz

exit 0
