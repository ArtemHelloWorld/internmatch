import rest_framework.response
import rest_framework.serializers
import rest_framework.status

import users.models

class ProfileShortSerializer(rest_framework.serializers.ModelSerializer):
    class Meta:
        model = users.models.User
        fields = [
            'id',
            'username',
            'email',
            'first_name',
            'last_name',
            'profile_image',
        ]
